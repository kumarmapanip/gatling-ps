package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
class AddCustomerApiSimulation extends Simulation {

  //conf
  val value_conf = http.baseUrl("http://localhost:9003")
    .header("Accept",value="application/json")
    .header(name="content-type", value ="application/json")


  //scenario
  val scn = scenario("Insert Customer")
    .exec(http("Post customer")
      .post("/rvy/api/cm/v1/customers")
      .body(RawFileBody(filePath = "./src/test/resources/bodies/addCustomer.json")).asJson
      .header(name="content-type",value = "application/json")
      .check(status is 200))
//    .pause(3)
//
//    .exec(http("Get Customer By Id")
//      .get("/rvy/api/cm/v1/customers/57")
//      .check(status is 200)
//    )



  //setup
  setUp(scn.inject(atOnceUsers(users=30))).protocols(value_conf)



}
