---
layout: page
title: User Guide
---

CadetHQ is a **desktop app for managing student contacts for CS1101S TAs, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

For CS1101S TAs that are good at using the Command Line, CadetHQ gives you the power to manage your students' grades, attendance, and contact information quickly, bypassing pesky menus that take ages to load and navigate.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

Note that throughout this document, we will often refer to CadetHQ as Address Book whenever appropriate.

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2526S1-CS2103T-T11-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your CadetHQ.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar cadethq.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add A1234567A n/John Doe p/98765432 e/johnd@u.nus.edu h/@JohnDoe` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

* All formats accept multiple spaces in between parameters. e.g. `score ‎ ‎  1 ‎ ‎ ‎   ex/  ‎ ‎  midterm  s/50` will be interpreted as `score 1 ex/midterm s/50`

* `SID` refers to the Student ID.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the Address Book.

Format: `add SID n/NAME p/PHONE_NUMBER e/EMAIL h/TELEGRAM_HANDLE [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have 0 or more tags!
</div>

Argument constraints:
* The SID **must be 9-characters long**, in the format A#######X where # is a digit (0-9) and X is a letter (A-Z). The first letter **must be 'A'**. The letters can be in uppercase or lowercase.
  e.g. a0000000B
* The name should only contain alphanumeric characters and spaces, and it should not be blank.
* The phone number should start with either **8 or 9** and have a length of **8**.
* The email must be of the format `local-part@domain` and adhere to the following constraints:
  1. The local-part should only contain **alphanumeric characters**.
  2. The local-part is followed by an `@` and the domain name `u.nus.edu`.
* The telegram handle must be of the format `@username`, with the username containing only **alphanumeric characters and underscores**. It **cannot be blank too**.

Examples:
* `add A0123456A n/John Doe p/98765432 e/johnd@u.nus.edu h/@JohnDoe`
![result for `add A0123456A n/John Doe p/98765432 e/johnd@u.nus.edu h/@JohnDoe`](images/addJohnDoe.png)
* `add A1234567B n/Betsy Crowe t/friend e/betsycrowe@u.nus.edu p/89891206 t/needshelp h/@BetsyC`
![result for `add A1234567B n/Betsy Crowe t/friend e/betsycrowe@u.nus.edu p/89891206 t/needshelp h/@BetsyC`](images/addBetsyCrowe.png)

Important Information:
* Duplicate students cannot be added to the Address Book.
* A student is considered a duplicate student if either the student ID or the student email are the same, where equality on these terms is defined as the literal string equality.
* In other words, a student is considered a distinct student only if both the student ID and the student email do not match any other existing student in the Address Book.

### Listing all persons : `list`

Shows a list of all persons in the Address Book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the Address Book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [h/TELEGRAM_HANDLE] [t/TAG]…​` or `edit SID [n/NAME] [p/PHONE] [e/EMAIL] [h/TELEGRAM_HANDLE] [t/TAG]…​`

* Edits the person at the specified `INDEX` or with the given `SID`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* The SID must be 9-characters long, in the format A#######X where # is a digit (0-9) and X is a letter (A-Z). The first letter must be 'A'. The letters can be in uppercase or lowercase.
  e.g. a0000000B
* The phone number should start with either **8 or 9** and have a length of **8**.
* The email must be of the format `local-part@domain` and adhere to the following constraints:
  1. The local-part should only contain **alphanumeric characters** and these special characters, excluding the parentheses **(+_.-)**. The local part **may not start or end with any special characters**.
  2. The local-part is followed by an `@` and the domain name `u.nus.edu`.
* The telegram handle must be of the format `@username`, with the username containing only **alphanumeric characters and underscores**. It **cannot be blank too**.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/alexyeohh@u.nus.edu` Edits the phone number and email address of the 1st person to be `91234567` and `alexyeohh@u.nus.edu` respectively.
![result for `edit 1 p/91234567 e/alexyeoh@u.nus.edu`](images/editIndex.png)
*  `edit A0000000A n/Betsy Crower t/ p/92345678 h/@BetsyC` Edits the name of the person with SID `A0000000A` to be `Betsy Crower`, with phone number `92345678` and Telegram handle `@BetsyC`, and clears all existing tags.
![result for `edit A0000000A n/Betsy Crower t/ p/92345678 h/@BetsyC`](images/editSid.png)

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the Address Book.

Format: `delete INDEX` or `delete SID`

* Deletes the person at the specified `INDEX` or with the given `SID`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The SID must be 9-characters long, in the format A#######X where # is a digit (0-9) and X is a letter (A-Z). The first letter must be 'A'. The letters can be in uppercase or lowercase.
  e.g. a0000000B

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the Address Book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.
* `list` followed by `delete A0123456Z` deletes the person in the Address Book with the student ID `A0123456Z`.

### Viewing a person: `view`

Finds and displays the specified person from the Address Book.

Format: `view INDEX` or `view SID`

* Finds the person at the specified `INDEX` or with the given `SID` and displays their information.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The SID must be 9-characters long, in the format A#######X where # is a digit (0-9) and X is a letter (A-Z). The first letter must be 'A'. The letters can be in uppercase or lowercase.
  e.g. a0000000B

Examples:
* `view 2` displays the 2nd person in the Address Book.
  ![result for 'view 2'](images/viewIndex.png)
* `view A0000000A` displays the person with SID A0000000A.
  ![result for 'view A0000000A'](images/viewSID.png)

### Marking the attendance for a person: `attend`

Adds the attendance for the specified tutorial, for the specified person from the Address Book. If attendance for the specified tutorial has already been taken, attendance for that tutorial will be removed instead.

Format: `attend INDEX TUTORIAL` or `attend SID TUTORIAL`

* Inverts the attendance for the specified `TUTORIAL`, for the person at the specified `INDEX` or with the given `SID`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The SID **must be 9-characters long**, in the format A#######X where # is a digit (0-9) and X is a letter (A-Z). The first letter **must be 'A'**. The letters can be in uppercase or lowercase.
  e.g. a0000000B
* The tutorial refers to the tutorial number. It **must be within the range of the number of tutorials specified in the Address Book.**
* The tutorial **must be a positive integer** 1, 2, 3, …​

Examples:
* `attend 1 6` adds attendance for tutorial `6` for the 1st person in the Address Book.
  ![result for 'attend 1 6'](images/attendIndex.png)
* `attend A0000001A 2` adds attendance for tutorial `2` for the person with SID `A0000001A`.
* `attend 1 6` removes the attendance for tutorial `6` for the 1st person in the Address Book if attendance has already been taken for this tutorial and this person.
  ![result for a second 'attend 1 6'](images/removeAttend.png)

### Adding a score for an exam for a person: `score`

Adds the specified score for the specified exam for the specified person from the Address Book. If a score already exists, it is overwritten by the most recent valid score command and the score is updated.

Format: `score INDEX ex/EXAM s/SCORE` or `score SID ex/EXAM s/SCORE`

* Adds the specified score `SCORE` for the specified exam `EXAM`, for the person at the specified `INDEX` or with the given `SID`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The SID **must be 9-characters long**, in the format A#######X where # is a digit (0-9) and X is a letter (A-Z). The first letter **must be 'A'**. The letters can be in uppercase or lowercase.
  e.g. a0000000B
* The score refers to the score attained by the specified person for the specified exam.
* The score **must be a non-negative integer**,  and **not more than the max score** of the specified exam.
* The exam refers to the exam name, and it **must match the exams recorded in the Address Book exactly**. e.g. If the exam name in the Address Book is "midterm", the specified exam name must be "midterm" and not "MIDTERM" or "mid term".

Examples:
* `score 1 ex/final s/80` sets the score of the 1st person in the Address Book to `80`, for the exam `final`.
* `score A0000001A ex/midterm s/70` sets the score of the person with SID `A0000001A` in the Address Book to `70`, for the exam `midterm`.
  ![result for 'score A0000001A ex/midterm s/70'](images/scoreSID.png)

### Editing the max score of an exam: `maxscore`

Changes the max score of the specified exam to the specified max score. It overwrites the pre-existing max score.

Format: `maxscore INDEX ex/EXAM ms/MAXSCORE` or `maxscore SID ex/EXAM ms/MAXSCORE`

* Changes the max score of the specified exam `EXAM` to the specified max score `MAXSCORE`.
* The max score **must be a non-negative integer**, and it **must be more than or equal to any recorded score** for the specified exam. e.g. If a person has score 70/80 for the exam, the new max score must be at least 70.
* The exam refers to the exam name, and it **must match the exams recorded in the Address Book exactly**. e.g. If the exam name in the Address Book is "midterm", the specified exam name must be "midterm" and not "MIDTERM" or "mid term".

Examples:
* `maxscore ex/midterm ms/90` changes the max score of the exam `midterm` to `90`.
  ![result for 'maxscore ex/midterm ms/90'](images/maxscore.png)

### Sorting the list : `sort`

* Sorts the list based either by name or exam score.
* Using the parameter `n/` with no other arguments sorts the list in ascending order by name.
* Using the parameter `ex/` with either `midterm` or `final` sorts the list in ascending order by exam score.

Format: `sort PARAMETER/[optional argument]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
People with no recorded scores when sorting by exam scores will be shifted to the bottom of the list.
</div>

Examples:
* `sort n/` sorts in ascending order of names.
* `sort ex/midterm` sorts in ascending order of midterm scores.
* `sort ex/final` sorts in ascending order of final scores.

  ![result for `sort ex/final`](images/sortByFinal.png)

### Clearing all entries : `clear`

Clears all entries from the Address Book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

CadetHQ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CadetHQ data are saved automatically as a JSON file `[JAR file location]/data/cadethq.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, cadethq.json will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the cadethq.json to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous cadethq.json home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add SID n/NAME p/PHONE_NUMBER e/EMAIL h/TELEGRAM_HANDLE [t/TAG]…​` <br> e.g., `add A0123456Y n/James Ho p/98333521 e/jamesho@u.nus.edu h/@JamesHoe t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`, `delete SID` <br> e.g., `delete 3`, `delete A1586788G`
**View** | `view INDEX`, `view SID` <br> e.g. `view 2`, `view A0000000A`
**Attend** | `attend INDEX TUTORIAL`, `attend SID TUTORIAL` <br> e.g. `attend 1 6`, `attend A0000000A 4`
**Score** | `score INDEX ex/EXAM s/SCORE`, `score SID ex/EXAM s/SCORE` <br> e.g. `score 2 ex/midterm s/60`, `score A0000000A ex/final s/80`
**Maxscore** | `maxscore ex/EXAM ms/SCORE` <br> e.g. `maxscore ex/midterm ms/90`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [h/TELEGRAM_HANDLE] [t/TAG]…​`, `edit SID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [h/TELEGRAM_HANDLE] [t/TAG]…​` <br> e.g.,`edit 2 n/James Lee p/95112511 e/jameslee@u.nus.edu h/@JamesLee6969`
**Sort** | `sort n/`, `sort ex/EXAM` <br> e.g. `sort n/`, `sort ex/final`, `sort ex/midterm`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
