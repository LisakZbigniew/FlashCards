package com.lisakzbigniew.flashcards.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lisakzbigniew.flashcards.model.Card;
import com.lisakzbigniew.flashcards.model.Language;
import com.lisakzbigniew.flashcards.model.Phrase;
import com.lisakzbigniew.flashcards.repository.CardRepository;
import com.lisakzbigniew.flashcards.repository.PhraseRepository;

@ExtendWith(MockitoExtension.class)
public class DatabaseFlashCardServiceImplTest {

    @Mock
    PhraseRepository phraseRepo;

    @Mock
    CardRepository cardRepo;

    @InjectMocks
    DatabaseFlashCardServiceImpl service;

    Card card;
    Long phraseIds;
    Long cardIds;

    @BeforeEach
    public void setUp() {
        card = new Card(
                new Phrase("Grandpa", Language.ENGLISH),
                new Phrase("Abuelo", Language.SPANISH));
        phraseIds = 1L;
        cardIds = 1L;
    }

    @Test
    public void shouldRejectNullCards() {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> service.addCard(null));
    }

    @Test
    public void shouldRejectCardsWithNullPhrases() {
        // given
        card.setFirstPhrase(null);
        // when & then
        assertThrows(IllegalArgumentException.class, () -> service.addCard(card));
    }

    @Test
    public void shouldSaveValidCard() {
        // given
        when(phraseRepo.findByContentAndLanguage(any(), any())).thenReturn(Optional.empty());
        when(phraseRepo.save(any(Phrase.class))).thenAnswer(invocation -> {
            Phrase old = (Phrase) invocation.getArgument(0);
            old.setId(phraseIds++);
            return old;
        });
        when(cardRepo.save(any(Card.class))).thenAnswer(invocation -> {
            Card old = (Card) invocation.getArgument(0);
            old.setId(cardIds++);
            return old;
        });

        // when
        Card savedCard = service.addCard(card);

        // then
        verify(phraseRepo, times(2)).save(any(Phrase.class));
        assertEquals(1L, savedCard.getId());
        assertEquals(1L, savedCard.getFirstPhrase().getId());
        assertEquals(2L, savedCard.getSecondPhrase().getId());

    }

}
