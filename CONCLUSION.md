# TICKET-101 Review Notes

### Done well

**Clean code structure** - Logic is well organized in separate methods  
**Good validation** - Proper checks for loan amounts and periods  
**Readable code** - Code is humanreadable.
**Error handling** - Clear exceptions for different failure cases

### Critical problem

**Missing core feature** - The credit score calculation to check if the loan was approved was not implemented:



### Changes made

**Credit score check** - Checks if the request for that loan amount and period is accepted.
**AgeValidator** - Checks if customers age meets the requirements.
**InvalidAgeException** - New exception that is thrown when the age requirement is not met.
**Front end loan_form** - Changed so it would display the shortest loan time correctly as 12 months and the
longest period as 48 instead of 60. Also got rid of the if clauses that checked which loan info will be displayed
and made it so it would always display what the API would give (always shows the highest loan amount).
