package simulations
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
class GetByIdApiSimulation extends Simulation {

  val value_conf = http.baseUrl("http://localhost:9003")
    .header("Accept",value="application/json")
    .header(name="content-type", value ="application/json")

  val scn = scenario("Get Customer By Id")
    .exec(http("get by id")
      .get("/rvy/api/cm/v1/customers/57")
      .check(status is 200)

    )

  setUp(scn.inject(atOnceUsers(users=30))).protocols(value_conf)
}
