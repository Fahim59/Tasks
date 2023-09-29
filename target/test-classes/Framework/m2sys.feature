Feature: User Login, Customer Addition, Product Addition, Sales Guidance.

  As a user of CloudApper app, I should be able to login into the system, adding new customer,
  adding new products, sales guidance.

  Scenario: User login into the system

    Given I'm a user
    And I'm on the login page
    When I enter login details and click login button
    Then I see the dashboard

#  Scenario: Customer Addition into the system
#
#    Given I'm a customer
#    And I click customer add button
#    When I enter customer details and click save button
#    Then I see the new added customer on customer table

#  Scenario: Product Addition into the system
#
#    Given I'm on product page
#    And I click product add button
#    When I enter product details and click save button
#    Then I see the new added product on product table

  Scenario: Sales Guidance of the system

    Given I'm on sales guidance page
    And I click sales add button
    When I enter sales details and click save button
    Then I see the new sales details on sales table