package akka.matchers

import org.scalatest.matchers._
import akka.data.Cargo


trait CargoDateMatchers {
  
  def arrival(expectedValue: Long) = {
    new HavePropertyMatcher[Cargo, Long] {
      def apply(cargo: Cargo) =
        HavePropertyMatchResult(
          cargo.arrival.getMillis == expectedValue,
          "arrival",
          expectedValue,
          cargo.arrival.getMillis
        )
    }
  }

  def departure(expectedValue: Long) = {
    new HavePropertyMatcher[Cargo, Long] {
      def apply(cargo: Cargo) =
        HavePropertyMatchResult(
          cargo.departure.getMillis == expectedValue,
          "departure",
          expectedValue,
          cargo.departure.getMillis
        )
    }
  }
  
}
