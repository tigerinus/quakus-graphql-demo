# quakus-graphql-demo Project

## Running the application in dev mode

    ./gradlew quarkusDev

Then go to <http://localhost:8080/q/graphql-ui> in a browser.

Example GraphQL query:

    mutation createPerson($firstName: String, $lastName: String) {
      createPerson(firstName: $firstName, lastName: $lastName) {
        id
      }
    }

    query getAllPeople {
      getAllPeople {
        firstName
        lastName
      }
    }

    subscription personCreated {
      personCreated {
        id
        firstName
        lastName
      }
    }

## Running the test code in Python

    cd test/python
    python3 -m venv venv
    . venv/bin/activate
    pip install -r requirements.txt
    python3 subscriber.py

## Running the test code in NodeJS

    cd test/nodejs
    npm install
    node subscriber.js
