# concept-tagger

# Prerequistites

- JDK 1.8
- SBT 

# Usage

```
sbt test
```

# Notes 

The performance of tagging is function of number of words in a sentence (m) and the number of concepts to be attributed (n).

Given we expect the n to be very large (millions) and m to be quite small (units/tens) we benefit from indexing the concept list in order 
to move the algorithm from O(m*n) towards O(m).

Extracting candidate phrases from the sentence to match to the concept set is challenging in the general sense as the sentence
 structure may be be complex - consider "East and West Indian" mapping to both concepts "East Indian" and "West Indian".
  
The test cases provided did not require this level of analysis and it was sufficient to consider only 
sub-sequences of the sentence of size and 1 and 2.

### Natural Language Processing

Out of interest I explored used of OpenNLP to help reduce the search space to noun-phrase sub-sequences. This method may be
useful in supporting more complex syntax to semantic mapping - but the latency it introduces is not particularly advantageous 
for this test.

### Parallelisation

The lookup of multiple candidate phrases against the concept Set is implemented Scala (or Java 8) parallel stream operators. 
By default this will use a global thread-pool sized to the available cores; this would need refinement for concurrent scenarios.
  
### Concurrency

For extremely large sentences and/or vast Concept Sets it may be efficient to consider a map-reduce step and/or sharding the
concept set. Clearly the I/O latency introduced from mapping work to a cluster would need to be justified.    

There may be benefits in pipe-lining the process, for example with dedicated hard-ware for NLP processing. 

The overall process is basically stateless and so can be horizontally scaled easily. 

# Implementation

- Unit testing coverage is not 100%, due to lack of time
- Tail Recursion (@tailrec - the compiler rewrites the code as a traditional loop to avoid stack overflow - 
but may not be optimal from a performance point of view) 
- OpenNLP concurrency - it is not clear whether the parser is thread-safe, and so a pool of parsers may be required 
 for concurrent use.   
- The 35MB OpenNLP model binary should be downloaded as part of the build, not included in the code.
