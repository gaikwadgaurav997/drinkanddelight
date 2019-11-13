Feature: Track Raw Material Order

Scenario: Tracking a particular raw material order

Given User is on login page
And User enters login credential
And User selects Track Raw Material Option from Raw Material dropdown
And User is on track raw material order page 
When User enters number in the order ID field as "7"
And user clicks on track order button present below
Then The shelf life of the given order Id 7 is displayed below



Scenario: Tracking a raw material order giving wrong order id

Given User is on login page
And User enters login credentials
And User selects Track Raw Material Option from Raw Material dropdown
And User is on track raw material order page
When User enters number in the order ID field as "500"
And user clicks on track order button present below
Then A message "RawMaterial Order ID does not exist" is displayed in the blank space below