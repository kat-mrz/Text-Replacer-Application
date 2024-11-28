# Text Replacer

## Project Overview

This application will take three String inputs: a word/phrase to be replaced, a word/phrase to replace it with, and a body of text. **Every occurence of that word/phrase in the body text will be replaced by the replacer word/phrase.** You can add multiple words to replace for one body of text.

This application is very versatile, but would be particularly useful for people who write many copies of **similarly written documents,** such as **job offer letters** or **report cards.** This project is of interest to me because as a Residence Advisor, I write many similar reports for each of my residents and could increase efficacy by replacing the names and relevant information on a pre-written template.

## User Stories
- As a user, I want to be able to add a body of text to replace words from.
- As a user, I want to be able to add a replaced/replacer word pairing to a list of word pairings.
- As a user, I want to be able to list all the replacer word pairings in my list of pairings as a report of my history.
- As a user, I want to be able to toggle case sensitivity on/off.
- As a user, I want to be able to save my current body text and replace history (if I so choose)
- As a user, I want to be able to be able to load my previous body text and replace history from file (if I so choose)

## Instructions for End User
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by inputting a valid word replacement and pressing the replace button. Successful changes should show up in the 2nd box from the left.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by inputting an invalid word replacement and pressing the replace button. Unsuccessful changes should show up in the 3rd box from the left.
- You can locate my visual component as the replace button.
- You can save the state of my application by pressing the save button.
- You can reload the state of my application by pressing the load button.

## Phase 4: Task 2
Wed Nov 27 11:39:04 PST 2024
Replacement manager created.
---------------
Wed Nov 27 11:39:59 PST 2024
Hey changed to Hi there,.
---------------
Wed Nov 27 11:39:59 PST 2024
Body text updated to Hi there, this is my application! Do you like it?
---------------
Wed Nov 27 11:39:59 PST 2024
Replace pair added to history.
---------------
Wed Nov 27 11:40:18 PST 2024
like changed to love.
---------------
Wed Nov 27 11:40:18 PST 2024
Body text updated to Hi there, this is my application! Do you love it?
---------------
Wed Nov 27 11:40:18 PST 2024
Replace pair added to history.
---------------

## Phase 4: Task 3
To refactor this project, I would likely improve the Single Responsibility Principle by splitting up the ReplacementManager class into two separate classes. Currently, the ReplacementManager class is acting as both a storage of successful replacements and the replacement pair that is referenced when checking for occurences in the body text. I would likely make a History class that would be a list of ReplacePairs to be my storage, and then make a Reference class to represent the pair that I am searching for in the body text.
Furthermore, I enjoyed having a separate history for unsuccessful changes, so I would likely improve coupling by creating the History abstract class mentioned earlier, with UnsuccessfulHistory and SuccessfulHistory classes extending it.