Feature: Basic Greetings API
This feature has no functionality other than to serve
as a basis to build upon.
 
Scenario Outline: Test some name parameters
	Given an input of <name>
	When the Greetings API is called
	Then the return value should be <result>
	
	Examples:
		| name		| result |
		| -null-	| Hello, World! |
		| Chris 	| Hello, Chris! |
		| abc 123	| Hello, abc 123! |
