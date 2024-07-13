Feature: Add an item to cart

Scenario: Add an item to cart from furniture department

Given user is in the department page
When user select "furniture" department and select item to add
And clicks on add2cart button
Then sucessfully an item to be added to the cart

Scenario: Add an item to cart from menswatches department

Given user is in the department page
When user select "menswatches" department and select item to add
And clicks on add2cart button
Then sucessfully an item to be added to the cart

Scenario: Add an item to cart from MovieAndTv department

Given user is in the department page
When user select "MovieAndTv" department and select item to add
And clicks on add2cart button
Then sucessfully an item to be added to the cart

