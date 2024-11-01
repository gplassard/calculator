package fr.gplassard.calculator

import scala.io.StdIn.readLine

object Repl {
  def main(args: Array[String]): Unit = {
    var continue = true
    var memory = Memory.empty
    while (continue) {
      val input = readLine("input> ")
      if (input == ":quit") {
        continue = false
      } else {
        val eval = CalculatorParser(input)
        if (eval.successful) {
          val grammar = eval.get
          CalculatorEvaluator.evaluate(grammar, memory) match {
            case Left(error) => println(s"Evaluation error : $error")
            case Right(None, newMemory) => {
              memory = newMemory
              println("Memory state :")
              println("==============")
              memory.variables.foreach((k, v) => println(s"$k: $v"))
            }
            case Right(Some(result), _) => {
              println(s"Evaluation result: $result")
            }
          }
        }
        else {
          println(s"Parsing error : ${eval.toString}")
        }
      }
    }
  }
}
