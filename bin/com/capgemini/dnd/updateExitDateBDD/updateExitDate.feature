Feature: Update Exit Date for a particular order

 

Scenario: Updating exit date for a particular order ID

 
Given User is on DND login page
And User enters login credentials as given 
And User selects Update Exit Date Option from Product dropdown
And User is on update exit date page 
When User enters the number in order ID as "5"
And User enters Exit Date as "2019-10-30"
And user clicks on update button to view output
Then The message "Data inserted" is displayed in the space below

 

 

Scenario: Updating exit date for a wrong order ID

 
Given User is on DND login page
And User enters login credentials as given 
And User selects Update Exit Date Option from Product dropdown
And User is on update exit date page 
When User enters the number in order ID as "500"
And User enters Exit Date as "2019-10-25"
And user clicks on update button to view output
Then The message "Product Order ID does not exist" is displayed in the space below

 

 

Scenario: Updating exit date by entering invalid exit date

 

Given User is on DND login page
And User enters login credentials as given 
And User selects Update Exit Date Option from Product dropdown
And User is on update exit date page 
When User enters the number in order ID as "5"
And User enters Exit Date as "2019-02-01"
And user clicks on update button to view output
Then The message "Exit date cannot be before its manufacturing date or after its expiry date" is displayed in the space below