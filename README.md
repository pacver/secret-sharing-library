# secret-sharing-library

As a part of my master thesis, I implemented a Java library for secret sharing and computations on shared secrets. The library allows 2, ..., n participants to share secrets with each other and to perform additions as well as multiplications on secretly shared values. 

The whole library has been implemented in Java 8 using the development environment IntelliJ Idea. The library uses parts of the open-source software Sunset/FFapl to perform the mathematical calculations within finite fields. The library is based on sessions, which means that within a session a single secret can be shared or combined expressions of multiple secrets can be calculated.
