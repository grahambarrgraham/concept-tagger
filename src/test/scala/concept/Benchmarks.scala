package concept

import org.scalatest.{FlatSpec, Matchers}

trait Benchmarks {
  this: FlatSpec with Matchers =>

  def benchmarkShortSentences(implicit workflow: Workflow, iterations: Int) {

    s"$workflow Benchmark Concurrent with short sentences" {
      runBenchmark(concurrentSmallSentencesTests, "concurrent-short-sentences", 7)
      0
    }

    s"$workflow Benchmark Sequential with short sentences" {
      runBenchmark(sequentialSmallSentencesTest, "sequential-short-sentences", 7)
      0
    }
  }

  def benchmarkLongSentences(implicit workflow: Workflow, iterations: Int) {

    s"$workflow Benchmark Concurrent with long sentences" {
      runBenchmark(concurrentLargeSentencesTests, "concurrent-long-sentences", 1)
      0
    }

    s"$workflow Benchmark Sequential with long sentences" {
      runBenchmark(sequentialLargeSentencesTest, "sequential-long-sentences", 1)
      0
    }
  }

  private def longRandomSentence(implicit workflow: Workflow) = {
    workflow.getConceptTags(util.UUIDGenerator.createSentence(1000))
  }

  private def smallSentences(implicit workflow: Workflow) = {
    workflow.getConceptTags("Which restaurants do East Asian food")
    workflow.getConceptTags("I would like some thai food")
    workflow.getConceptTags("Where can I find good sushi")
    workflow.getConceptTags("MZZZ anyd wiiiz cannndot beee sessses")
    workflow.getConceptTags("Which restaurants do West Indian food")
    workflow.getConceptTags("Which restaurants do East or West Indian food or Spanish Sushi")
  }

  private def warmup(implicit iterations: Int, workflow: Workflow) = 1 to 2 foreach { _ => smallSentences}
  private def sequentialSmallSentencesTest(implicit iterations: Int, workflow: Workflow) = () => 1 to iterations foreach { _ => smallSentences }
  private def concurrentSmallSentencesTests(implicit iterations: Int, workflow: Workflow) = () => (1 to iterations).par.foreach { _ => smallSentences }
  private def sequentialLargeSentencesTest(implicit iterations: Int, workflow: Workflow) = () => 1 to iterations foreach { _ => longRandomSentence }
  private def concurrentLargeSentencesTests(implicit iterations: Int, workflow: Workflow) = () => (1 to iterations).par.foreach { _ => longRandomSentence }

  private def calcSentencesPerSecond(timeInMillis: Long, numberOfSentences: Int, numberOfIterations: Int) = 1000*(numberOfIterations*numberOfSentences)/timeInMillis

  private def runBenchmark(testFunction: () => Unit, description: String, numberOfSentences: Int)(implicit iterations: Int, workflow: Workflow) = {
    warmup
    val now = System.currentTimeMillis()
    testFunction()
    val elapsedTime = System.currentTimeMillis() - now
    val sentencesPerSecond  = calcSentencesPerSecond(elapsedTime, numberOfSentences, iterations)
    println(s"$description : $workflow took $elapsedTime ms for ${iterations*numberOfSentences} sentences. Sentences per second : $sentencesPerSecond")
  }

}
