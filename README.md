# freeze-clock-example
Example project that shows how to easily freeze your tests on a specific date(time) or on the current date(time).

A DateTimeProvider class that should be used to retrieve date-time related stuff in your project.
This is needed because it uses a specific Clock instance that is freezed in the FixedTimeExtension for testing purposes.
