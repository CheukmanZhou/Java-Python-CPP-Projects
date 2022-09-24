'''
 Cheukman Zhou
 This program is used to make a Hang Man Game for an English Summative
 January 23, 2022
 Version 1.0
'''

import random
from pickle import TRUE

print("\n")

print("---------------------")
print("    HANG MAN GAME    ")
print("---------------------")

print("\n")

print("WELCOME!\n")


foodList = ["noodles", "sushi", "sandwich", "pizza", "cake"]
sportsList = ["badminton", "soccer", "basketball", "tennis", "volleyball"]
countriesList = ["canada", "china", "brazil", "russia", "australia"]


end = False


while end == False:
    print("CATEGORIES:\n(1) Food\n(2) Sports\n(3) Countries\n")
    
    categoryChecker = True
    
    while categoryChecker == True:
        try:
            categorySelection = int(input("Select the Category You want your Word to be in corresponding to its Number: "))
            
            if categorySelection < 1 or categorySelection > 3:
                raise Exception
        except Exception:
            print("\nSorry, that is Not one of the Options\n")
        else:
            categoryChecker = False
    
    
    if categorySelection == 1:
        print("\nCategory Chosen: Food\n")
        randomNum = random.SystemRandom().randint(0, (len(foodList) - 1)) #random.SystemRandom generates true random numbers instead of pseudo random numbers
        secretWord = foodList[randomNum]
    elif categorySelection == 2:
        print("\nCategory Chosen: Sports\n")
        randomNum = random.SystemRandom().randint(0, (len(sportsList) - 1))
        secretWord = sportsList[randomNum]
    else:
        print("\nCategory Chosen: Countries\n")
        randomNum = random.SystemRandom().randint(0, (len(countriesList) - 1))
        secretWord = countriesList[randomNum]
    
    
    unknownWord = ""
    
    for x in secretWord:
        unknownWord = unknownWord + "_"
    
    
    displayWord = ""
    
    for x in secretWord:
        displayWord = displayWord + "_ "
    
    
    guessLeft = 6
    gameOver = False
    
    while gameOver == False:
        print(displayWord)
        
        print("\nYou have " + str(guessLeft) + " Guess(es) Left\n")
    
        print("Guess:\n(1) Letter\n(2) Word\n")
        
        guessChoiceChecker = True
        
        while guessChoiceChecker == True:
            try:
                guessChoice = int(input("Select the Type of Guess You want to make corresponding to its Number: "))
                
                if guessChoice < 1 or guessChoice > 2:
                    raise Exception
            except Exception:
                print("\nSorry, that is Not one of the Options\n")
            else:
                guessChoiceChecker = False
        
        print("")
        
        guessedRight = False
        
        if guessChoice == 1:
            guess = ""
            
            guessChecker = True
        
            while guessChecker == True:
                try:
                    guess = input("Enter Letter Guess: ")
                    guess = guess.lower()
                    
                    if len(guess) != 1:
                        raise Exception
                except Exception:
                    print("\nSorry, Enter Only One Character\n")
                else:
                    guessChecker = False
            
            
            index = 0
            
            for x in secretWord:
                if x == guess:
                    guessedRight = True
                    
                    if index == 0:
                        unknownWord = x + unknownWord[1:]
                    elif index == (len(secretWord) - 1):    
                        unknownWord = unknownWord[:index] + x
                    else:
                        unknownWord = unknownWord[:index] + x + unknownWord[index + 1:]
                index = index + 1
                
        else:
            guess = input("Enter Word Guess: ")
            guess = guess.lower() 
            
            if guess == secretWord:
                guessedRight = True
                unknownWord = secretWord
        
                
        displayWord = ""  
                
        for x in unknownWord:
            displayWord = displayWord + x + " "        
                
        print("\n" + displayWord)        
        
        
        if guessedRight == True:
            print("\nCongrats! You Guessed Right!")
                
        else:
            print("\nSorry, that is Not the Right Guess")
            
            guessLeft = guessLeft - 1
        
        
        guessedRight = False
        
        
        if unknownWord == secretWord:
            gameOver = True
            print("\nCongratulations!! You Win!!")
            
        elif guessLeft == 0:
            gameOver = True
            print("\nSorry, but You are Out of Guesses. You Lose")
        
        
        print("\n\n")
    
        
    print("Play Again?\n")
    print("\n(1) Yes\n(2) No\n")   


    playAgainChecker = True
        
    while playAgainChecker == True:
        try:
            playAgainChoice = int(input("Enter Your Response corresponding to its Number: "))
            
            if playAgainChoice < 1 or playAgainChoice > 2:
                raise Exception
        except Exception:
            print("\nSorry, that is Not one of the Options\n")
        else:
            playAgainChecker = False

    
    if playAgainChoice == 2:
        end = True
        print("\n\n\nThanks for Playing!")
        
    else:
        print("\n\n\n\n\n")
