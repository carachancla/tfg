#!/usr/bin/env bash
cd ..
git add .
git reset --hard
git pull
mvn clean install
