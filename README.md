Note - The World Bank took down their climate WebAPI. Darn it. We now depend on a docker version of the same until we work out what to do long term. Docker build and deploy this locally - https://github.com/servirtium/worldbank-climate-recordings - see README

TL;DR:

```
docker build git@github.com:servirtium/worldbank-climate-recordings.git#main -t worldbank-weather-api-for-servirtium-development
docker run -d -p 4567:4567 worldbank-weather-api-for-servirtium-development
```

The build for this demo project needs that docker container running

## Technology

The same as a Python, Java and Ruby version of a "Climate API" library demo, but for the Kotlin ecosystem demonstrating a SERVIRTIUM markdown format for service virtualization. See https://servirtium.dev

## Setup

This requires JDK 11. You'll have to go to Oracle.com to install the JDK, or 

```
brew tap homebrew/cask-versions
brew cask install java11

```

## Running the demo's tests

```
./gradlew test
```

That runs the tests: direct, playback and record. TODO: more description.

## Credits:

@daviddenton's work, implementing all of the steps of https://github.com/servirtium/README/blob/master/starting-a-new-implementation.md
