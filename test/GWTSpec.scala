package test

import org.specs2._
import specification.script.{GWT, StandardDelimitedStepParsers, StepParser}

class ApplicationSpec extends Specification with GWT with StandardDelimitedStepParsers { def is =
  
   s2"""

    Create a new account with valid user data.
      Given Valid user data                                             ${signup.start}
          in JSON format # {"username":"bob", "password":"secure"} #
      When I do POST to { /signup } with given user data
      Then I expect HTTP code {201}                                     ${signup.end}
    """

  val aJsonString = StepParser((s: String) => s)("""#([^#]+)#""".r)

  lazy val signup = Scenario("signUp").
    given(aJsonString).
    when(aString) { case postURL :: userData :: _ =>  makeAPICall(userData, postURL) }.
    andThen(anInt) { case expectedHTTPCode :: responseHTTPCode :: _ => expectedHTTPCode == responseHTTPCode }

  def makeAPICall(jsonString: String, apiCallString: String): Int = 201

}