
# concept-tagger

## Prerequistites

- JDK 1.8
- SBT 

## Usage

```
sbt test
```

## Algorithm 

The performance of the concept tagging algorithm is a function of the number of words in the sentence (m) and the number of concepts that could be attributed (n).

Given that we expect n to be very large (millions) and m to be small (units/tens) we benefit from indexing the concept list in order 
to move the algorithm from O(m*n) towards O(m) on average.

Extracting candidate phrases from the sentence to match to the concept set is challenging in the general sense as the sentence
 structure may be be complex - consider "East and West Indian" mapping to both concepts "East Indian" and "West Indian".
  
The test cases provided did not require this level of analysis and it was sufficient to consider only 
sub-sequences of the sentence of size and 1 and 2.

### Natural Language Processing

Out of interest I explored using OpenNLP to help reduce the search space to noun-phrase sub-sequences. This method may be
useful in supporting more complex syntax to semantic mapping - but the latency it introduces is not particularly advantageous 
for this test. As it turns out this wasn't a particularly useful approach for the test cases provided.

### Parallelisation

The lookup of multiple candidate phrases (i.e. sentence sub-sequences) against the concept Set can be implemented using parallel stream operators.  
By default this use a global thread-pool sized to the available cores; this would need refinement for concurrent scenarios. 
However I wasn't able to demonstrate a performance uplift with this mechanism, see Testing section later.
  
### Concurrency

For extremely large sentences and/or vast Concept Sets it may be efficient to consider a map-reduce step and/or sharding the
concept set. Clearly the I/O latency introduced from mapping task to a cluster would need to be justified.    

There may be benefits in pipe-lining the process, for example with dedicated hard-ware for NLP processing. 

The overall process is basically stateless and so can be horizontally scaled for increased throughput.

## Implementation Notes

- Unit testing coverage is not 100%, due to lack of time
- Tail Recursion (@tailrec - the compiler rewrites the code as a traditional loop to avoid stack overflow - 
but may not be optimal from a performance point of view) 
- OpenNLP concurrency - it is not clear whether the parser is thread-safe, and so a pool of parsers may be required 
 for concurrent use.   
- The 35MB OpenNLP model binary should be downloaded as part of the build, not included in the code.

## Performance Testing

All tests : 
* used an input concept file contain 1M UUIDs plus the handful of test Concepts ("East Asian" etc)
* used the basic algorithm of take all sub-sequences from the sentence of lengths 1 and 2 (NLP tests also make use of this)

The performance test dimentions :
* Use of NLP parser or not
* Use of short or long input sentences
* Use of a parallel or sequential map function when looking up the concepts
* Running multiple sentences parses in concurrently or sequence 

The results show :
* there was no uplift from parallelisation, even for long sentences which was a surprise and would need further investigation.
* NLP processing is pretty slow
* the algorithm is very inefficient for long sentences, but performs well for short ones

1. sequential-short-sentences : (Sequential-uuid-1M.txt-WordAndWordPairExtractor) took 921 ms for 70000 sentences. Sentences per second : 76004
2. concurrent-short-sentences : (Sequential-uuid-1M.txt-WordAndWordPairExtractor) took 1348 ms for 70000 sentences. Sentences per second : 51928
3. concurrent-short-sentences : (Parallel-uuid-1M.txt-WordAndWordPairExtractor) took 1403 ms for 35000 sentences. Sentences per second : 24946
4. sequential-short-sentences : (Parallel-uuid-1M.txt-WordAndWordPairExtractor) took 2330 ms for 35000 sentences. Sentences per second : 15021
5. concurrent-long-sentences : (Sequential-uuid-1M.txt-WordAndWordPairExtractor) took 2500 ms for 500 sentences. Sentences per second : 200
6. sequential-long-sentences : (Parallel-uuid-1M.txt-WordAndWordPairExtractor) took 518 ms for 100 sentences. Sentences per second : 193
7. concurrent-long-sentences : (Parallel-uuid-1M.txt-WordAndWordPairExtractor) took 525 ms for 100 sentences. Sentences per second : 190
8. sequential-long-sentences : (Sequential-uuid-1M.txt-WordAndWordPairExtractor) took 2968 ms for 500 sentences. Sentences per second : 168
9. concurrent-short-sentences : (Sequential-uuid-1M.txt-NlpNounPhraseExtractor) took 671 ms for 70 sentences. Sentences per second : 104
10. sequential-short-sentences : (Sequential-uuid-1M.txt-NlpNounPhraseExtractor) took 1013 ms for 70 sentences. Sentences per second : 69
