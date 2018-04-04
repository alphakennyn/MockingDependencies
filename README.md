# Logging Tutorial

* log4j log levels are: Trace < Debug < Info < Warn < Error < Fatal
* A good logging should provides us with the exact answers for *WHO, WHAT, WHEN* questions. Otherwise it's simple useless logging. You can learn about this on [DZONE](https://dzone.com/articles/application-logging-what-when) and [OWASP](https://www.owasp.org/index.php/Logging_Cheat_Sheet) websites.

# Steps

0. The log4j setting file is located inside *src/main/java* and is named log4j2.xml by convention. You can learn about file naming convention on [log4j documents(https://logging.apache.org/log4j/2.x/manual/configuration.html)].

1. Set the Root Logger to use the defined ConsoleAppender. So, you can see the logs inside the console. Then, run the main method inside tutorial.core.banking package to see the logs.

2. Enrich the logs, by adding additional fields to the PatternLayout of ConsoleAppender. So, we can diagnose our application more easily. Add the following fields to the pattern layout of ConsoleAppender. You can get help from [log4j documents](https://logging.apache.org/log4j/2.x/manual/layouts.html).
  - Log Level
  - Date
  - Mehtod Name
  - Class Name
  - Line Number
  - Thread Name 

3. Logging in wild should help us to answer What, When, Who questions. So, usually we need to enrich our logs with some information about the context of the application. For example, for each log we need to know the username, the ip address, the requested url and the session id. Use the current HttpContext to add contextual logging to your logs.
