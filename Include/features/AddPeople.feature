Feature: User and Setting - Add People
  I want to see the Congratulation page when adding some Users

  Background: User logs in to TinyPulse
    Given User logs in to TinyPulse

  @1111
  Scenario: To Verify Configuration page displays
    When User clicks on Setting icon
    And User clicks on Add People
    And Add some users into list
    Then The Configurations page displays