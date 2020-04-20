Feature: Groups

  Scenario Outline: Group creation
    Given a set of groups
    When I create a new group with name <name>, header <header>, footer <footer>
    Then the new set of groups is equal to the old set with the added group

    Examples:
      | name        | header        | footer        |
      | test name   | test header   | test footer   |
      | test name 1 | test header 1 | test footer 1 |

