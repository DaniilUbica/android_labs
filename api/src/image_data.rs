use rocket::http::ContentType;
use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ImageData<'a> {
    pub(crate) image: &'a str,
    pub(crate) description: &'a str,
    pub(crate) name: &'a str
}