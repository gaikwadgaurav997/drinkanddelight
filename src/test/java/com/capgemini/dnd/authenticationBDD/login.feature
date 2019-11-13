Feature: Successful Login for valid credentials 
#This is how background can be used to eliminate duplicate steps 

Background: User navigates to drinkanddelight login page 
	
Given I am on drinkanddelight login page 
	
	#Scenario Outline with AND 
Scenario Outline: Successful Login for valid credentials (examples) 
	When I enter username as "<username>" 
	And I enter password as "<password>" 
	And clicks on the submit button 
	
	Examples: 
		| username  | password  | 
		| cbcgf 	| abc 		| 
		| ankit_40 	| abcdefgh 	| 
		| ankit_40 	| abcd 		| 
	 