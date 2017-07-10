package concept

import concept.extractors.{NlpNounPhraseExtractor, WordAndWordPairExtractor}
import org.scalatest.{FlatSpec, Matchers}

class PerformanceTest extends FlatSpec with Matchers with Benchmarks {

  val workflowWithLargeConceptSetSequentialMap: Workflow = Workflow(new WordAndWordPairExtractor, "uuid-1M.txt", Sequential)
  val workflowWithNlpLargeConceptSetSequentialMap: Workflow = Workflow(new NlpNounPhraseExtractor, "uuid-1M.txt", Sequential)
  val workflowWithLargeConceptSetParallelMap: Workflow = Workflow(new WordAndWordPairExtractor, "uuid-1M.txt", Parallel)

  "benchmark workflowWithLargeConceptSetSequentialMap short sentences" should behave like benchmarkShortSentences(workflowWithLargeConceptSetSequentialMap, 10000)
  "benchmark workflowWithNlpLargeConceptSetSequentialMap short sentences" should behave like benchmarkShortSentences(workflowWithNlpLargeConceptSetSequentialMap, 10)
  "benchmark workflowWithLargeConceptSetParallelMap  short sentences" should behave like benchmarkShortSentences(workflowWithLargeConceptSetParallelMap, 5000)

  "benchmark workflowWithLargeConceptSetSequentialMap long sentences" should behave like benchmarkLongSentences(workflowWithLargeConceptSetSequentialMap, 500)
  "benchmark workflowWithLargeConceptSetParallelMap long sentences" should behave like benchmarkLongSentences(workflowWithLargeConceptSetParallelMap, 100)

}
