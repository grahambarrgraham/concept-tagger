package concept

import concept.extractors.{NlpNounPhraseExtractor, WordAndWordPairExtractor}
import org.scalatest.{FlatSpec, Matchers}

class PerformanceTest extends FlatSpec with Matchers
  with Behaviours {

  val workflowWithLargeConceptSetSequentialMap: Workflow = Workflow(new WordAndWordPairExtractor, "uuid-1M.txt", Sequential)
  val workflowWithNlpLargeConceptSetSequentialMap: Workflow = Workflow(new NlpNounPhraseExtractor, "uuid-1M.txt", Sequential)
  val workflowWithLargeConceptSetParallelMap: Workflow = Workflow(new WordAndWordPairExtractor, "uuid-1M.txt", Parallel)

  "benchmark workflowWithLargeConceptSetSequentialMap" should behave like benchmark(workflowWithLargeConceptSetSequentialMap, 10000)
  "benchmark workflowWithNlpLargeConceptSetSequentialMap" should behave like benchmark(workflowWithNlpLargeConceptSetSequentialMap, 10)
  "benchmark workflowWithLargeConceptSetParallelMap" should behave like benchmark(workflowWithLargeConceptSetParallelMap, 5000)

}
