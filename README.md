# concept-tagger

# Usage

```
sbt test
```

There is a lag on test start up due loading of an OpenNLP binary

# Scala and SBT

# Semantic Limitations

- concepts are limited to noun phrases
- complex noun phrases with conjunctions (e.g. East and West Indian) would not be properly resolved. In this example "East Indian" would not be found.

# Approach considerations

## Use of OpenNLP 

As implemented this acts as a filter, radically reducing the number of sub-sequences of the input sentence that need to be considered. For more complex analysis, such as solving the solution above, it is likely that NLP would be extremely desirable. 


# Performance Considerations

## Open NLP

- OpenNLP parallelisation, parse thread-safety. Easily horizontally scalable for throughput, but latency would be a consideration. 
- The noun-phrases are shorter and so will match more quickly, but there may be more of them
- Other implementations may be faster

## Matching 

- Large numbers of concepts
- Longer sentences
- Parallelisation of the matching is achieved using parallel filtering - which will optimise parallelisation to the number of available cores.
- For more massive scale there will be a boundary at which a map-reduce step is required to match subsets of the concept space on different nodes.

# Code Considerations
 
- including a 35MB binary as a source-controlled resource is usually undesirable, it is preferable for this to be downloaded from a resource repository during the build process - but time did not permit.
