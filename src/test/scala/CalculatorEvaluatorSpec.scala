package fr.gplassard.calculator

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CalculatorEvaluatorSpec extends AnyFlatSpec with Matchers  {

  "Calculator evaluator" should "evaluate a literal" in {
    CalculatorEvaluator.evaluateExpression(Literal(12), Memory.empty) shouldEqual Right(12)
  }

  it should "evaluate a sum" in {
    CalculatorEvaluator.evaluateExpression(Sum(Literal(1), Literal(11)), Memory.empty) shouldEqual Right(12)
  }

  it should "evaluate a product" in {
    CalculatorEvaluator.evaluateExpression(Product(Literal(2), Literal(12)), Memory.empty) shouldEqual Right(24)
  }

  it should "evaluate a division" in {
    CalculatorEvaluator.evaluateExpression(Division(Literal(12), Literal(3)), Memory.empty) shouldEqual Right(4)
  }

  it should "reject a division by 0" in {
    CalculatorEvaluator.evaluateExpression(Division(Literal(12), Literal(0)), Memory.empty) shouldEqual Left(DivideByZero)
  }

  it should "evaluate a variable" in {
    CalculatorEvaluator.evaluateExpression(VariableUsage("a"), Memory(Map("a" -> 5))) shouldEqual Right(5)
  }

  it should "reject an unknown variable" in {
    CalculatorEvaluator.evaluateExpression(VariableUsage("a"), Memory.empty) shouldEqual Left(UnknownVariable)
  }

}
