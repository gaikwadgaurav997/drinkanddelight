Feature: Update Product Stock

 

Scenario: Updating Product stock details for a valid order ID

 
Given User is on DND homepage to login
And User enters his login credentials given to him 
And User selects Update Stock Option from Product dropdown
And user is on update Product Stock page 
When User enters the order ID as "5"
And User gives manufacturing Date as "2019-09-20"
And User gives expiry Date as "2020-02-10"
And user selects Passed/Failed from Dropdown menu
And user clicks update stock button below
Then The message "Data inserted" is shown

 

 

Scenario: Updating Product stock details for an invalid order ID

 

Given User is on DND homepage to login
And User enters his login credentials given to him 
And User selects Update Stock Option from Product dropdown
And user is on update Product Stock page 
When User enters the order ID as "500"
And User gives manufacturing Date as "2019-09-20"
And User gives expiry Date as "2020-02-10"
And user selects Passed/Failed from Dropdown menu
And user clicks update stock button below
Then The message "Product Order ID does not exist" is shown

 

 

Scenario: Updating product stock details by entering invalid manufacturing and expiry date

 

Given User is on DND homepage to login
And User enters his login credentials given to him 
And User selects Update Stock Option from Product dropdown
And user is on update Product Stock page 
When User enters the order ID as "5"
And User gives manufacturing Date as "2019-09-20"
And User gives expiry Date as "2019-09-10"
And user selects Passed/Failed from Dropdown menu
And user clicks update stock button below
Then The message "You cant enter expiry date before manufacturing date" is shown