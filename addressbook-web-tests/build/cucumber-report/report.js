$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("bdd/groups.feature");
formatter.feature({
  "line": 1,
  "name": "Groups",
  "description": "",
  "id": "groups",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 3,
  "name": "Group creation",
  "description": "",
  "id": "groups;group-creation",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of groups",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new group with name \u003cname\u003e, header \u003cheader\u003e, footer \u003cfooter\u003e",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of groups is equal to the old set with the added group",
  "keyword": "Then "
});
formatter.examples({
  "line": 8,
  "name": "",
  "description": "",
  "id": "groups;group-creation;",
  "rows": [
    {
      "cells": [
        "name",
        "header",
        "footer"
      ],
      "line": 9,
      "id": "groups;group-creation;;1"
    },
    {
      "cells": [
        "test name",
        "test header",
        "test footer"
      ],
      "line": 10,
      "id": "groups;group-creation;;2"
    },
    {
      "cells": [
        "test name 1",
        "test header 1",
        "test footer 1"
      ],
      "line": 11,
      "id": "groups;group-creation;;3"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 11772321400,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "Group creation",
  "description": "",
  "id": "groups;group-creation;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of groups",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new group with name test name, header test header, footer test footer",
  "matchedColumns": [
    0,
    1,
    2
  ],
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of groups is equal to the old set with the added group",
  "keyword": "Then "
});
formatter.match({
  "location": "GroupStepDefinitions.loadGroups()"
});
formatter.result({
  "duration": 561886000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test name",
      "offset": 31
    },
    {
      "val": "test header",
      "offset": 49
    },
    {
      "val": "test footer",
      "offset": 69
    }
  ],
  "location": "GroupStepDefinitions.createGroup(String,String,String)"
});
formatter.result({
  "duration": 2042180600,
  "status": "passed"
});
formatter.match({
  "location": "GroupStepDefinitions.verifyGroupCreated()"
});
formatter.result({
  "duration": 54814200,
  "status": "passed"
});
formatter.after({
  "duration": 833464500,
  "status": "passed"
});
formatter.before({
  "duration": 6959623200,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Group creation",
  "description": "",
  "id": "groups;group-creation;;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of groups",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new group with name test name 1, header test header 1, footer test footer 1",
  "matchedColumns": [
    0,
    1,
    2
  ],
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of groups is equal to the old set with the added group",
  "keyword": "Then "
});
formatter.match({
  "location": "GroupStepDefinitions.loadGroups()"
});
formatter.result({
  "duration": 35866800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test name 1",
      "offset": 31
    },
    {
      "val": "test header 1",
      "offset": 51
    },
    {
      "val": "test footer 1",
      "offset": 73
    }
  ],
  "location": "GroupStepDefinitions.createGroup(String,String,String)"
});
formatter.result({
  "duration": 1642021300,
  "status": "passed"
});
formatter.match({
  "location": "GroupStepDefinitions.verifyGroupCreated()"
});
formatter.result({
  "duration": 51539300,
  "status": "passed"
});
formatter.after({
  "duration": 803921000,
  "status": "passed"
});
});