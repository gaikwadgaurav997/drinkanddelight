Feature: Place Product Order

Scenario: Placing a particular product order

Given User is on drink and delight login pages
And User enters login credentials
And User selects Place Product Option from Product dropdown
And User is on place product order page 

When User entered the Product Name as "JUICE"
And User selects Distributor ID from Dropdown menu as "d003"
And User selects Warehouse ID from Dropdown menu as "w02"
And User entered the Quantity as "60"
And User selects Quantity Unit from Dropdown menu as "Litres"
And User entered the Price per Unit as "50"
And User gives Expected Date of Delivery as "2020-01-10"
And User clicked on place order button

Then "Product Order added successfully" is displayed below