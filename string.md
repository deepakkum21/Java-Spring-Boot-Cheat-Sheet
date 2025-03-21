String are immutable
String s1="Deepak"
String s2 = new String("Deepak") //not interned / not sent to string pool
String s3 = `new String("Deepak").intern()` // checks the string pool if present return the address otherwise saves to string pool

Immutable ? - use cache of string pool - threadsafe - use cached hashcode

- '+' operator gets implemented using StringBuilder.append internally
