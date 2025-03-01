#[macro_use] extern crate rocket;

use api::routes::*;

#[launch]
fn rocket() -> _ {
    rocket::build().configure(rocket::Config {
        address: "0.0.0.0".parse().unwrap(),
        port: 8000,
        ..rocket::Config::default()
    }).mount("/", routes![get_image, get_images])
}