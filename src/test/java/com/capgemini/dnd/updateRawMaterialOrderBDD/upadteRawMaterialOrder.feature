Feature:  Update order delivery status of Product order for given raw Product order id 
Scenario: Successfull Login for valid credentials and delivery status update 
	Given I am on drinkanddelight login page 
	When I enter username as "ankit_40" 
	And I enter password as "abcd" 
	And clicks on the submit button 
	And I am on update Product order delivery status page 
	And I enter Product order id as "7" 
	And I enter Delivery Status as "Cancelled" 
	And I click on Update Order button 
	
	
    