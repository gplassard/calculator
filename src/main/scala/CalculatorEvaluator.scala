package fr.gplassard.calculator

sealed trait EvaluationError
case object DivideByZero extends EvaluationError
case object UnknownVariable extends EvaluationError

case class Memory(variables: Map[String, Double])
object Memory {
  val empty = Memory(Map.empty)
}

object CalculatorEvaluator {

  def evaluateExpression(exp: Expression, memory: Memory): Either[EvaluationError, Double] = exp match {
    case Literal(v) => Right(v.doubleValue())
    case Sum(left, right) => for {
      l <- evaluateExpression(left, memory)
      r <- evaluateExpression(right, memory)
    } yield l + r
    case Product(left, right) =>  for {
      l <- evaluateExpression(left, memory)
      r <- evaluateExpression(right, memory)
    } yield l * r
    case Division(left, right) => (evaluateExpression(left, memory), evaluateExpression(right, memory)) match {
      case (Right(l), Right(r)) if r != 0 => Right(l / r)
      case (Right(_), Right(r)) if r == 0 => Left(DivideByZero)
      case (Right(_), rightEvaluation) => rightEvaluation
      case (leftEvaluation, _) => leftEvaluation
    }
    case VariableUsage(name) => memory.variables.get(name) match {
      case Some(value) => Right(value)
      case None => Left(UnknownVariable)
    }
  }
}
