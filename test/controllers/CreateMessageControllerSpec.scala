package controllers

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

class CreateMessageControllerSpec extends PlayFunSpec with GuiceOneAppPerSuite {

  describe("CreateMessageController") {
    describe("route of CreateMessageController#index") {
      it("should be valid") {
        val result = route(app, addCsrfToken(FakeRequest(GET, routes.CreateMessageController.index().toString))).get
        status(result) mustBe OK
      }
    }
    describe("route of CreateMessageController#create") {
      val result =
        route(app,
              addCsrfToken(
                FakeRequest(POST, routes.CreateMessageController.create().toString)
                  .withFormUrlEncodedBody("title" -> "a", "body" -> "b")
              )).get
      status(result) mustBe SEE_OTHER
    }
  }

}
