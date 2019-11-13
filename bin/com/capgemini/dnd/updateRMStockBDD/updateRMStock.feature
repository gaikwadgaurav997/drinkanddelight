Feature: Update Raw Material Stock

Scenario: Updating raw material stock details for a particular order ID

Given User is on DND homepage
And User enters his login credentials 
And User selects Update Stock Option from Raw Material dropdown
And user is on update RM Stock page 
When User enters the field order ID as "5"
And User enters manufacturing Date as "2019-09-20"
And User enters expiry Date as "2020-02-10"
And user selects Passed from Dropdown menu
And user clicks on update stock button
Then The message "Data inserted" is displayed



Scenario: Updating raw material stock details for a wrong order ID

Given User is on DND homepage
And User enters his login credentials 
And User selects Update Stock Option from Raw Material dropdown
And user is on update RM Stock page 
When User enters the field order ID as "500"
And User enters manufacturing Date as "2019-09-20"
And User enters expiry Date as "2020-02-10"
And user selects Passed from Dropdown menu
And user clicks on update stock button
Then The message "RawMaterial Order ID does not exist" is displayed



Scenario: Updating raw material stock details by entering invalid manufacturing and expiry date

Given User is on DND homepage
And User enters his login credentials 
And User selects Update Stock Option from Raw Material dropdown
And user is on update RM Stock page 
When User enters the field order ID as "5"
And User enters manufacturing Date as "2019-09-20"
And User enters expiry Date as "2019-09-10"
And user selects Passed from Dropdown menu
And user clicks on update stock button
Then The message "You cant enter expiry date before manufacturing date" is displayed