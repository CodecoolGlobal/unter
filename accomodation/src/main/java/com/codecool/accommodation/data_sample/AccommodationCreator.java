package com.codecool.accommodation.data_sample;

import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.entity.Address;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.repository.AccommodationRepository;
import org.springframework.stereotype.Component;

@Component
public class AccommodationCreator {
    private final AccommodationRepository repository;

    public AccommodationCreator(AccommodationRepository repository) {
        this.repository = repository;
    }

    public void initialize() {
        String city = "Budapest";
        String[] zipCodes = {
                "1137",
                "1055",
                "1011",
                "1066",
                "1074",
                "1136",
                "1014"
        };
        String[] streets = {
                "Szent István krt.",
                "Nagy Ignác utca",
                "Arany János utca",
                "Zichy Jenő utca",
                "Dob utca",
                "Tátra utca",
                "Fortuna köz"
        };
        int[] houseNumbers = {
                6,
                2,
                19,
                19,
                5,
                7,
                2
        };
        double[] latitudes = {
                47.512873,
                47.508543,
                47.502229,
                47.504224,
                47.496468,
                47.512924,
                47.502894
        };
        double[] longitudes = {
                19.049109,
                19.052279,
                19.050777,
                19.056981,
                19.059277,
                19.050098,
                19.031669
        };
//        String[] descriptions = {
//                "1 guest · 1 bedroom · 1 bed · 1.5 shared bathrooms · Wifi · Kitchen · Free parking · Washing Machine",
//                "2 guest · 3 bedroom · 1 bed · 1.5 shared bathrooms · Wifi · Kitchen",
//                "4 guest · 4 bedroom · 4 bed · 2 bathrooms · Free parking · Washing Machine",
//                "1 guest · 1 bedroom · 1 bed · 1.5 shared bathrooms · Wifi · Kitchen · Free parking · Washing Machine",
//                "3 guest · 1 bedroom · 1 bed · 1.5 shared bathrooms · Wifi · Free parking · Dry Cleaning",
//                "2 guest · 1 bedroom · 1 bed · 1.5 shared bathrooms · Wifi · Washing Machine",
//                "3 guest · 1 bedroom · 1 bed · 1.5 shared bathrooms · Wifi · Kitchen · Free parking · Washing Machine"
//        };
        String[] descriptions = {
                "This is one of Budapest’s most historic and architecturally eclectic neighborhood. You’ll be close to everything in the city centre but far enough to enjoy a relaxing trip. We are both born and raised here so we know all the good spots like the delicious pancakes, vanilla infused orange juice and freshly brewed coffee, down the street at one of our favorite restaurants",
                "Brand new, very clean, studio apartment with a private bathroom, kitchenette and private work space. The studio is renovated with natural stone floors, high end finishings and closets, throughout the apartment.",
                "A lovely space to unwind and relax after a busy day whether it is work or play. Awake refreshed and ready for a day exploring the city via this clean, sunny apartment with impressive views. Head out and wander through the nearby farmers’ market and pick up local ingredients to later craft a meal in the fully stocked kitchen.",
                "This modern, sun-drenched apartment offers a tranquil residential vibe alongside quick, easy access to the downtown areas. Admire the crisp, contemporary decor of the open-plan living space and take in the peaceful surroundings from the cute terrace.",
                "Indulge in the comfort and tranquility of this contemporary apartment. The space features an open-concept layout, a monochromatic color scheme with stark contrasts, wood surfaces, and tasteful furnishings and decor.",
                "Experience true urban living in this design-conscious flat in central (CN). The edited space features midcentury furnishings and colorful accents, lending it a distinctly liveable feel. Take in sweeping city views from the private balcony.",
                "Have a glass of wine while enjoying Netflix on the comfortable sofa after exploring local culture. Pull the back the curtains after a restful night’s sleep and let light flood into this studio. This central apartment makes excellent use of the space with a calming neutral palette and sleek finished floors alongside thoughtful details."
        };
        int[] maxNumbersOfGuests = {
                1,
                2,
                4,
                1,
                3,
                2,
                3
        };
        String[] names = {
                "Stay at this spacious Edwardian House",
                "Independant luxury studio apartment",
                "Budapest Studio Apartments",
                "10 mins to Deák Square",
                "Spacious Peaceful Modern Bedroom",
                "The Blue Room In Budapest",
                "5 Star Luxury Apartment"
        };
        String[] pictureUrls = {
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ_wbPYTxQPMcBh7SPzLFActXnP3uhifeVT_g&usqp=CAU",
                "https://www.expatkings.com/wp-content/uploads/2018/10/Airbnb-rental-tips.-Hostmaker-1-620x349.jpg",
                "https://www.smartertravel.com/uploads/2017/07/Untitled-design-8.jpg",
                "https://cdn.bisnow.net/fit?height=489&type=jpeg&url=https%3A%2F%2Fs3.amazonaws.com%2Fcdn.bisnow.net%2Fcontent%2Fimages%2F2017%2F05%2F59151d0978bbf_https_press_atairbnb_com_app_uploads_2016_12_midtown_4.jpeg&width=717&sign=FeltIPi9cOWA36nVIeDvZxwgtiCZrpUyMRdvyZviTUI",
                "https://media.cntraveler.com/photos/5a8f258bd363c34048b35aac/master/w_2250,h_1500,c_limit/airbnb-plus-london.jpg",
                "https://static.trip101.com/paragraph_media/pictures/001/676/061/large/969ae4bb-efd1-4fb9-a4e3-5cb3316dd3c9.jpg?1562227937",
                "https://image.insider.com/585029a0dd0895bc548b4b8b?width=750&format=jpeg&auto=webp"
        };
        Long[] hostIds = {
                1L,
                2L,
                1L,
                3L,
                4L,
                1L,
                8L
        };

        for (int i = 0; i < descriptions.length; i++) {
            NewAccommodation newAccommodation = NewAccommodation.builder()
                    .city(city)
                    .street(streets[i])
                    .zipCode(zipCodes[i])
                    .houseNumber(houseNumbers[i])
                    .latitude(latitudes[i])
                    .longitude(longitudes[i])
                    .description(descriptions[i])
                    .maxNumberOfGuests(maxNumbersOfGuests[i])
                    .name(names[i])
                    .picture(pictureUrls[i])
                    .hostId(hostIds[i])
                    .build();

            Accommodation accommodation = createAccommodation(newAccommodation);
            repository.saveAndFlush(accommodation);
        }


        NewAccommodation newAccommodation = NewAccommodation.builder()
                .city("Kazincbarcika")
                .street("Utca")
                .zipCode("4444")
                .houseNumber(12)
                .latitude(22.00)
                .longitude(32.00)
                .description("Nice!444négy")
                .maxNumberOfGuests(4000)
                .name("Házikó")
                .picture(null)
                .hostId(1L)
                .build();

        Accommodation accommodation = createAccommodation(newAccommodation);
        repository.save(accommodation);
    }


    private Accommodation createAccommodation(NewAccommodation newAccommodation) {
        Address address = Address.builder()
                .city(newAccommodation.getCity())
                .street(newAccommodation.getStreet())
                .zipCode(newAccommodation.getZipCode())
                .houseNumber(newAccommodation.getHouseNumber())
                .build();

        Coordinate coordinate = Coordinate.builder()
                .latitude(newAccommodation.getLatitude())
                .longitude(newAccommodation.getLongitude())
                .build();

        Accommodation accommodation = Accommodation.builder()
                .description(newAccommodation.getDescription())
                .maxNumberOfGuests(newAccommodation.getMaxNumberOfGuests())
                .name(newAccommodation.getName())
                .hostId(newAccommodation.getHostId())
                .address(address)
                .coordinate(coordinate)
                .pictureUrl(newAccommodation.getPicture())
                .build();

        return accommodation;
    }
}
