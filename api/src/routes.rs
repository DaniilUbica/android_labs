use std::path::Path;
use rocket::fs::NamedFile;
use rocket::get;
use rocket::http::ContentType;
use rocket::response::content;
use rocket::serde::json::Json;
use crate::image_data::*;
#[get("/image/<filename>")]
pub async fn get_image(filename: String) -> Option<(ContentType, NamedFile)> {
    let image_path = Path::new("images/").join(filename);

    if let Ok(file) = NamedFile::open(&image_path).await {
        let content_type = match image_path.extension().and_then(|ext| ext.to_str()) {
            Some("png") => ContentType::PNG,
            Some("jpg") | Some("jpeg") => ContentType::JPEG,
            Some("gif") => ContentType::GIF,
            _ => ContentType::Binary,
        };

        Some((content_type, file))
    } else {
        None
    }
}

#[get("/images")]
pub fn get_images() -> Json<String> {
    let images = vec![
        ImageData {
            image: "images/pic_1.jpeg",
            description: "Просто Бзыря - маленький пес",
            name: "Бзыря",
        },
        ImageData {
            image: "images/pic_2.jpeg",
            description: "То ли кот, то ли свинья, каждый видит в нем что-то свое",
            name: "Свинокот",
        },
        ImageData {
            image: "images/pic_3.jpeg",
            description: "Кот с депрессией просит закурить",
            name: "Огонек",
        },
    ];

    let json = serde_json::to_string(&images).unwrap();
    Json(json)
}
