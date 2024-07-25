#!/bin/sh
export POSTGRES_PASS=${POSTGRES_PASS:=$(cat $POSTGRES_PASS_FILE)}
exec java -jar app.jar