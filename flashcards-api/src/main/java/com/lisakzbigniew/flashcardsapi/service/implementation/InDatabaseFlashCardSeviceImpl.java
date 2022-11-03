package com.lisakzbigniew.flashcardsapi.service.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.cloud.translate.v3.DetectLanguageRequest;
import com.google.cloud.translate.v3.DetectLanguageResponse;
import com.google.cloud.translate.v3.DetectedLanguage;
import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.Translation;
import com.google.cloud.translate.v3.TranslationServiceClient;
import com.lisakzbigniew.flashcardsapi.model.Card;
import com.lisakzbigniew.flashcardsapi.model.CardCollection;
import com.lisakzbigniew.flashcardsapi.model.Language;
import com.lisakzbigniew.flashcardsapi.model.Phrase;
import com.lisakzbigniew.flashcardsapi.model.Tag;
import com.lisakzbigniew.flashcardsapi.repository.CollectionRepository;
import com.lisakzbigniew.flashcardsapi.repository.FlashcardRepository;
import com.lisakzbigniew.flashcardsapi.repository.PhraseRepository;
import com.lisakzbigniew.flashcardsapi.repository.TagRepository;
import com.lisakzbigniew.flashcardsapi.service.FlashCardService;


@Service
public class InDatabaseFlashCardSeviceImpl implements FlashCardService{
    @Autowired
    private FlashcardRepository flashCardRepo;
    @Autowired
    private PhraseRepository phraseRepo;
    @Autowired
    private TagRepository tagRepo;
    @Autowired
    private CollectionRepository cardCollectionRepo;
    @Value("${api.projectId}")
    private String projectId; 


    @Value("${tresholds.comboStreak}")
    private Integer streakTreshold;

    private Logger logger = LoggerFactory.getLogger(InDatabaseFlashCardSeviceImpl.class.getName());

    @Override
    public Card saveCard(Card card) {
        card.setA(savePhrase(card.getA()));
        card.setB(savePhrase(card.getB()));

        List<Tag> updatedList = new ArrayList<>();

        for(Tag tag : card.getTags()){
            updatedList.add(saveTag(tag));
        }

        card.setTags(updatedList);

        return flashCardRepo.save(card);
    }
    @Override
    public Iterable<Card> listCards() {
        return flashCardRepo.findAll();
    }
    @Override
    public Card updateCard(Card updatedCard) {
        return saveCard(updatedCard);
    }
    @Override
    public void removeCard(Card card) {
        flashCardRepo.delete(card);
    }
    @Override
    public boolean cardExists(Card card) {
        return card.getId()!= null && flashCardRepo.existsById(card.getId());
    }
    @Override
    public Optional<Card> findCardById(Long id) {
        if(id == null) return Optional.empty();
        return flashCardRepo.findById(id);
    }
    @Override
    public Iterable<Card> findCardByLanguage(Language lang) {
        //TODO consider changing to a @Query in the repo
        Iterable<Card> all = listCards();
        List<Card> filtered = new ArrayList<>();
        for(Card card : all){
            if(card.getA().getLang() == lang || card.getB().getLang() == lang)
                filtered.add(card);
        }
        return filtered;
    }
    @Override
    public Iterable<Card> findCardByTag(Tag tag) {
        //TODO consider changing to a @Query in the repo
        Iterable<Card> all = listCards();
        List<Card> filtered = new ArrayList<>();
        for(Card card : all){
            if(card.getTags().contains(tag))
                filtered.add(card);
        }
        return filtered;
    }
    @Override
    public Card addToStreak(Card card) {
        int newStreak = card.getStreak() + 1;
        if(newStreak == streakTreshold){
            card.setLevel(card.getLevel().next());
            card.setStreak(0);
        }else{
            card.setStreak(newStreak);
        }

        return saveCard(card);
    }
    @Override
    public Card resetStreak(Card card) {
        card.setStreak(0);
        return saveCard(card);
    }
    @Override
    public Phrase savePhrase(Phrase phrase) {
        return phraseRepo.save(phrase);
    }
    @Override
    public Iterable<Phrase> listPhrases() {
        return phraseRepo.findAll();
    }
    @Override
    public Phrase updatePhrase(Phrase phrase) {
        return savePhrase(phrase);
    }
    @Override
    public boolean phraseExists(Phrase phrase) {
        return phrase.getId() != null && phraseRepo.existsById(phrase.getId());
    }
    @Override
    public Optional<Phrase> findPhraseById(Long id) {
        if(id == null) return Optional.empty();
        return phraseRepo.findById(id); 
    }
    @Override
    public Iterable<Phrase> findPhraseByLanguage(Language lang) {
        return phraseRepo.findByLang(lang);
    }
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepo.save(tag);
    }
    @Override
    public Iterable<Tag> listTags() {
        return tagRepo.findAll();
    }
    @Override
    public Tag updateTag(Tag updatedTag) {
        return saveTag(updatedTag);
    }
    @Override
    public void removeTag(Tag tag) {
        tagRepo.delete(tag);        
    }
    @Override
    public boolean tagExists(Tag tag) {
        return tag.getId() != null && tagRepo.existsById(tag.getId());
    }
    @Override
    public Optional<Tag> findTagById(Long id) {
        if(id == null) return Optional.empty();
        return tagRepo.findById(id);
    }
    @Override
    public CardCollection saveCardCollection(CardCollection cardCollection) {
        List<Card> updatedList = new ArrayList<>();

        for(Card card : cardCollection.getCards()){
            updatedList.add(saveCard(card));
        }

        cardCollection.setCards(updatedList);

        return cardCollectionRepo.save(cardCollection);
    }
    @Override
    public Iterable<CardCollection> listCardCollections() {
        return cardCollectionRepo.findAll();
    }
    @Override
    public CardCollection updateCardCollection(CardCollection updatedCardCollection) {
        return saveCardCollection(updatedCardCollection);
    }
    @Override
    public void removeCardCollection(CardCollection cardCollection) {
        cardCollectionRepo.delete(cardCollection);
    }
    @Override
    public boolean cardCollectionExists(CardCollection cardCollection) {
        return cardCollection.getId() != null && 
                cardCollectionRepo.existsById(cardCollection.getId());
    }
    @Override
    public Optional<CardCollection> findCardCollectionById(Long id) {
        if(id == null) return Optional.empty();
        return cardCollectionRepo.findById(id);
    }
    @Override
    public Iterable<CardCollection> findCardCollectionByLanguage(Language lang) {
        Iterable<CardCollection> all = listCardCollections();
        List<CardCollection> filtered = new ArrayList<>();
        
        for(CardCollection collection : all){
            if(collection.getCards().stream().anyMatch( (card) ->
                card.getA().getLang() == lang || card.getB().getLang() == lang
            )){
                filtered.add(collection);
            }
        }

        return filtered;
    }
    @Override
    public Iterable<CardCollection> findCardCollectionByOwner(String owner) {
        return cardCollectionRepo.findByOwner(owner);
    }
    @Override
    public Iterable<CardCollection> findCardCollectionByCard(Card card) {
        Iterable<CardCollection> all = listCardCollections();
        List<CardCollection> filtered = new ArrayList<>();
        
        for(CardCollection collection : all){
            if(collection.getCards().stream().anyMatch( (c) ->
                c.equals(card)
            )){
                filtered.add(collection);
            }
        }

        return filtered;
    }
    @Override
    public Optional<Language> servicedLanguage(String lang) {
        return Language.parseString(lang);
    }
    @Override
    public Optional<Phrase> translate(Phrase sourcePhrase, Language targetLang) {

        try{
            List<Translation> possibleTranslations = translateText(sourcePhrase,targetLang);
            Phrase translatedPhrase = new Phrase();
            translatedPhrase.setContent(possibleTranslations.get(0).getTranslatedText());
            translatedPhrase.setLang(targetLang);
            return Optional.ofNullable(translatedPhrase);
        }catch(IOException e){
            logger.info("Google services not available");
            
        }

        return Optional.empty();

    }

    private List<Translation> translateText(Phrase text, Language targetLanguage)
    throws IOException {

        try (TranslationServiceClient client = TranslationServiceClient.create()) {

            LocationName parent = LocationName.of(projectId, "global");

            
            TranslateTextRequest request =
                TranslateTextRequest.newBuilder()
                                    .setParent(parent.toString())
                                    .setMimeType("text/plain")
                                    .setSourceLanguageCode(text.getLang().googleCode())
                                    .setTargetLanguageCode(targetLanguage.googleCode())
                                    .addContents(text.getContent())
                                    .build();
            TranslateTextResponse response = client.translateText(request);
            return response.getTranslationsList();
        }
    }

    public FlashcardRepository getFlashCardRepo() {
        return flashCardRepo;
    }
    public void setFlashCardRepo(FlashcardRepository flashCardRepo) {
        this.flashCardRepo = flashCardRepo;
    }
    public PhraseRepository getPhraseRepo() {
        return phraseRepo;
    }
    public void setPhraseRepo(PhraseRepository phraseRepo) {
        this.phraseRepo = phraseRepo;
    }
    public TagRepository getTagRepo() {
        return tagRepo;
    }
    public void setTagRepo(TagRepository tagRepo) {
        this.tagRepo = tagRepo;
    }
    public CollectionRepository getCardCollectionRepo() {
        return cardCollectionRepo;
    }
    public void setCardCollectionRepo(CollectionRepository cardCollectionRepo) {
        this.cardCollectionRepo = cardCollectionRepo;
    }
    
    
}
