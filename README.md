# SENG300P1-7

## Running
To run the project run the Simulation class with the integer number of DIY Stations passed as an command line argument

## Notes
Running the simulation with n DIY stations will open:
    1 Attendant UI window
    1 Maintenance Simulation window
    n Customer UI windows; and
    n Customer Simulation windows
All classes in the com.diy.software.simulation package are purely for simulation.
Classes in com.diy.software.util are for the logic and functionality
Classes com.diy.software.view are primarily GUI elements/windows
Please ignore the buttons in the payment windows as they are not functional; please use the Simulation window instead

## Operation
1) To start scanner click the start scanning button in the Customer UI
2) Then in the Customer Simulation window click on any items you want to add under the "Click to Scan" list
3) Click the corresponding item under the "Click to add to scale" to place the item in the bagging area
4) Click the "Purchase Bags" button to open the "Purchase Bags" window
    4.1) Use the number pad to enter the ammount of bags you wish to purchase
    4.2) Click the "Enter" button to add the bags to your item list
5) Click the "Enter Member  #" button to open the "Enter Membership #" window
    5.1) Use the number pad to enter a membership number
    5.2) Click the "Enter" button to submit the entered membership number
6) Select one of the payment options
    a) If you pick cash click on a bill or coin in the Simulation window to insert it into a banknote slot are coinslot
    b) If you pick credit or debit click a card in the Simulation window to insert it into the card reader
7) Once the balance in the bottom-left corner of the Customer UI window is <= 0 you can click the "Complete/Print Receipt" Button to finish with your transaction
8) Click all of the items in the "Click to remove" list to remove your items from the bagging area
9) Once all items have been removed the UI will switch back to the starting screen.

## Sources
    How can I check to see if a dialog is closed? Stack Overflow. (1963, June 1). Retrieved November 24, 2022, from https://stackoverflow.com/questions/36950743/how-can-i-check-to-see-if-a-dialog-is-closed 
