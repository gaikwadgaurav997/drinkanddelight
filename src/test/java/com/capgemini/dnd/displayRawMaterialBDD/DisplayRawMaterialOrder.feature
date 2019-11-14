Feature: Display Raw Material Order
Display all raw material orders accroding to supplier Id and delivery status.

Scenario:Display raw material orders

Given User is on drink and deligt login pages
And User enters his/her login credentials
And User selects Display all orders from RawMaterial dropdown
And User is on display raw material order page 

When User entered the Delivery Status as "All"
And User selects the SupplierId as "All"
And User enters start date as "2019-11-10"
<<<<<<< HEAD
And User enters end date as "2020-11-10"
And User clicks on the Submit button to get the results
=======
And User enters end date as "2019-11-10"

>>>>>>> 56670262cf7aa4845eae4a00a099f0e9739bb33b

Then "No Records Found" is displayed