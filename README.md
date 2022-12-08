# SENG 300 Project Itteration 3

## Running
To run the project run the Simulation class with the integer number of DIY Stations passed as an command line argument

## Notes
Running the simulation with n DIY stations will open:
    1 Attendant UI window
    1 Maintenance Simulation window
    n Customer Simulation windows
The attendant button in the customer window currently doesn't do anything
Some computers (only one in our group) may experience a visual glitch with the undecorated windows and we have no idea how to fix it


## Operation
1) Log in using the attendant window (Username: "John Doe" Password: "Password123") Login can be done with either virtual or real keyboard
2) Click the "Start Up" button on a station. A Dissabled station window should appear
3) Click the "Enable" button to enable the station. The station window should switch to the start screen
4) In the station window click start scanning to proceed to the scanning window
5) Click any of the buttons to start a use case
6) Follow the instruction(s) on the window or use the customer simulator window to simulate physical actions
7) Once the remaining balance is less than or equal to 0 click "Complete/Print Reciept" to finish the session
8) Remove each item from the bagging area individually or clear all items using the customer simulator window
9) Once all items have been removed the customer window should reset to the start window

## Specific Operations
### Adding/Removing items from the Attendant Window
1) In order to access a customer's session from the attendant window click the "Open Cart" button next to the station desired which should lead to a customer cart window
2) Items can be removed from this window or added with the "Add an Item" button. After adding an item you will have to click the "Refresh" button to get an updated view of the customer cart.
### Approving/Denying discrepancies
Some issues will be prompted to the attendant directly through a popup window others will appear next to the station in the attendant window. The later ones can be addressed with the buttons next to the message.
### Adding items by lookup
To add an item by PLU, Browsing, or Text Search please do the following:
1) Select the item from the appropriate menu (for plu type the number into the field and click enter)
2) In the customer simulator window enter the weight the item should have into the "Enter Weight in Scanning Area" field. The weight is automatically adjusted
3) Click "Item Placed" in the customer window
4) A weight discrepancy window should appear. Click the "Add Weight to Bagging Area" button

## Testing
Please use the AllTests test suite for running the unit tests.