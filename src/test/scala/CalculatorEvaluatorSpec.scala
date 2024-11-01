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

  it should "evaluate a simple statement" in {
    CalculatorEvaluator.evaluateStatement(VariableDefinition("foo", Literal(21)), Memory.empty) shouldEqual Right(Memory(Map("foo" -> 21)))
  }

  it should "evaluate a statement relying on a variable" in {
    CalculatorEvaluator.evaluateStatement(VariableDefinition("foo", VariableUsage("bar")), Memory(Map("bar" -> 5))) shouldEqual Right(Memory(Map("foo" -> 5, "bar" -> 5)))
  }

  it should "evaluate a complex statement" in {
    CalculatorEvaluator.evaluateStatement(VariableDefinition("foo", Product(Sum(VariableUsage("bar"), VariableUsage("bar")), Literal(3))), Memory(Map("bar" -> 5))) shouldEqual Right(Memory(Map("foo" -> 30, "bar" -> 5)))
  }

  it should "reject a statement relying on an unknown variable" in {
    CalculatorEvaluator.evaluateStatement(VariableDefinition("foo", VariableUsage("bar")), Memory.empty) shouldEqual Left(UnknownVariable)
  }

  it should "reject a statement relying on a division by zero" in {
    CalculatorEvaluator.evaluateStatement(VariableDefinition("foo", Division(Literal(3), Literal(0))), Memory.empty) shouldEqual Left(DivideByZero)
  }

}
