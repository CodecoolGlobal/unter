package com.codecool.accommodation.data_sample;

import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.entity.Address;
import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.repository.AccommodationRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AccommodationCreator {
    private final AccommodationRepository repository;

    public AccommodationCreator(AccommodationRepository repository) {
        this.repository = repository;
    }

    public void initialize() {
        String[] countries = {"Hungary", "Germany"};
        String[] cities = {"Budapest", "Berlin"};
        String[] zipCodes = {
            "1137", //Budapest
            "1055",
            "1011",
            "1066",
            "1074",
            "1136",
            "1014",
            "10409", //Berlin
            "13086",
            "10969",
            "10117",
            "10117",
            "12043",
            "10315"

        };
        String[] streets = {
            "Szent István krt.", // budapest
            "Nagy Ignác utca",
            "Arany János utca",
            "Zichy Jenő utca",
            "Dob utca",
            "Tátra utca",
            "Fortuna köz",
            "Sültstraße", // berlin
            "Weissensee",
            "Rudi-Dutschke-Straße",
            "Krausenstraße",
            "Jägerstraße",
            "Wilhelm-Busch-Straße",
            "Friedrichsfelde"
        };
        String[] houseNumbers = {
            "6", //budapest
            "2",
            "19",
            "19",
            "5",
            "7",
            "2",
            "18", //Berlin
            "23",
            "26",
            "56",
           "58",
            "13",
            "44"
        };
        double[] latitudes = {
            47.512873, // budapest
            47.508543,
            47.502229,
            47.504224,
            47.496468,
            47.512924,
            47.502894,
            52.54789647, // berlin
            52.55689483,
            52.50696465,
            52.50973296,
            52.514101,
            52.47734311,
            52.50447538
        };
        double[] longitudes = {
            19.049109, // budapest
            19.052279,
            19.050777,
            19.056981,
            19.059277,
            19.050098,
            19.031669,
            13.4332532, //berlin
            13.44114584,
            13.3914742,
            13.39504114,
            13.391039,
            13.44669535,
            13.50273241
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
            //budapest
            "This is one of Budapest’s most historic and architecturally eclectic neighborhood. You’ll be close to everything in the city centre but far enough to enjoy a relaxing trip. We are both born and raised here so we know all the good spots like the delicious pancakes, vanilla infused orange juice and freshly brewed coffee, down the street at one of our favorite restaurants",
            "Brand new, very clean, studio apartment with a private bathroom, kitchenette and private work space. The studio is renovated with natural stone floors, high end finishings and closets, throughout the apartment.",
            "A lovely space to unwind and relax after a busy day whether it is work or play. Awake refreshed and ready for a day exploring the city via this clean, sunny apartment with impressive views. Head out and wander through the nearby farmers’ market and pick up local ingredients to later craft a meal in the fully stocked kitchen.",
            "This modern, sun-drenched apartment offers a tranquil residential vibe alongside quick, easy access to the downtown areas. Admire the crisp, contemporary decor of the open-plan living space and take in the peaceful surroundings from the cute terrace.",
            "Indulge in the comfort and tranquility of this contemporary apartment. The space features an open-concept layout, a monochromatic color scheme with stark contrasts, wood surfaces, and tasteful furnishings and decor.",
            "Experience true urban living in this design-conscious flat in central (CN). The edited space features midcentury furnishings and colorful accents, lending it a distinctly liveable feel. Take in sweeping city views from the private balcony.",
            "Have a glass of wine while enjoying Netflix on the comfortable sofa after exploring local culture. Pull the back the curtains after a restful night’s sleep and let light flood into this studio. This central apartment makes excellent use of the space with a calming neutral palette and sleek finished floors alongside thoughtful details.",
            //berlin
            "Indulge in the comfort and tranquility of this contemporary apartment. The space features an open-concept layout, a monochromatic color scheme with stark contrasts, wood surfaces, and tasteful furnishings and decor.",
            "Experience true urban living in this design-conscious flat in central (CN). The edited space features midcentury furnishings and colorful accents, lending it a distinctly liveable feel. Take in sweeping city views from the private balcony.",
            "Have a glass of wine while enjoying Netflix on the comfortable sofa after exploring local culture.",
            "Modern studio with balcony in the heart of (CN). Save money and time walking to the most famous landmarks. Clean and bright, the studio is recently renovated as you can see in the photos.",
            "Enter a wonderful home with open living area accented by art. Spread out on the chaise-longue, cook in your own kitchen, sink into the soft bed after seeing.",
            "We know how important it is to feel comfortable & relaxed when you arrive back from a long day of sightseeing. This idea is what inspired us to build our apartment studio and provide everyone that stays a place to recharge, relax and enjoy...",
            "Enjoy fresh breakfast each morning as well as delicious snacks throughout your stay."
        };

        int[] maxNumbersOfGuests = {
            1, // budapest
            2,
            4,
            1,
            3,
            2,
            3,
            3, // berlin
            1,
            2,
            1,
            2,
            3,
            2
        };

        String[] names = {
            "Stay at this spacious Edwardian House", // budapest
            "Independant luxury studio apartment",
            "Budapest Studio Apartments",
            "10 mins to Deák Square",
            "Spacious Peaceful Modern Bedroom",
            "The Blue Room In Budapest",
            "5 Star Luxury Apartment",
            "Adorable apartment in City Centre of Berlin", // berlin
            "Cute One bedded Room in Berlin",
            "Double Room in Berlin",
            "Private Room in Berlin",
            "Entire apartment in Berlin",
            "Charming Studio Apt in heart of NeuKölln",
            "New Green 2 centrally located in Berlin"
        };

        String[][] pictureUrls = {
            {
                    "https://ksassets.timeincuk.net/wp/uploads/sites/56/2017/04/Exterior-IH-April-p62-Powell-920x920.jpg",
                    "https://ksassets.timeincuk.net/wp/uploads/sites/56/2017/04/Living-room-IH-April-p62-Powell-920x920.jpg",
                    "https://ksassets.timeincuk.net/wp/uploads/sites/56/2017/04/Sofa-IH-April-p62-Powell-920x920.jpg",
                    "https://ksassets.timeincuk.net/wp/uploads/sites/56/2017/04/Kitchen-diner-IH-April-p62-Powell-920x920.jpg",
                    "https://ksassets.timeincuk.net/wp/uploads/sites/56/2017/04/Kitchen-IH-April-p62-Powell-920x920.jpg",
                    "https://ksassets.timeincuk.net/wp/uploads/sites/56/2017/04/Stairs-IH-April-p62-Powell-920x920.jpg",
                    "https://ksassets.timeincuk.net/wp/uploads/sites/56/2017/04/Bedroom-IH-April-p62-Powell-920x920.jpg",
            },
            {
                    "https://media.rightmove.co.uk/52k/51411/100883060/51411_12493_IMG_01_0000.jpg",
                    "https://media.rightmove.co.uk/52k/51411/100883060/51411_12493_IMG_02_0000.jpg",
                    "https://media.rightmove.co.uk/52k/51411/100883060/51411_12493_IMG_03_0000.jpg",
                    "https://media.rightmove.co.uk/52k/51411/100883060/51411_12493_IMG_04_0000.jpg",
                    "https://media.rightmove.co.uk/52k/51411/100883060/51411_12493_IMG_05_0000.jpg",
                    "https://media.rightmove.co.uk/52k/51411/100883060/51411_12493_IMG_06_0000.jpg",
            },
            {
                    "https://media.rightmove.co.uk/106k/105809/101689583/105809_71-b1-ik_IMG_00_0000.jpeg",
                    "https://media.rightmove.co.uk/106k/105809/101689583/105809_71-b1-ik_IMG_01_0000.jpeg",
                    "https://media.rightmove.co.uk/106k/105809/101689583/105809_71-b1-ik_IMG_02_0000.jpeg",
                    "https://media.rightmove.co.uk/106k/105809/101689583/105809_71-b1-ik_IMG_03_0000.jpeg",
                    "https://media.rightmove.co.uk/106k/105809/101689583/105809_71-b1-ik_IMG_04_0000.jpeg",
            },
            {
                    "https://media.rightmove.co.uk/103k/102653/99838775/102653_CAM10_1Bed_ShortLet_IMG_08_0000.jpeg",
                    "https://media.rightmove.co.uk/103k/102653/99838775/102653_CAM10_1Bed_ShortLet_IMG_05_0000.jpeg",
                    "https://media.rightmove.co.uk/103k/102653/99838775/102653_CAM10_1Bed_ShortLet_IMG_03_0000.jpeg",
                    "https://media.rightmove.co.uk/103k/102653/99838775/102653_CAM10_1Bed_ShortLet_IMG_09_0000.jpeg",
                    "https://media.rightmove.co.uk/103k/102653/99838775/102653_CAM10_1Bed_ShortLet_IMG_04_0000.jpeg",
                    "https://media.rightmove.co.uk/103k/102653/99838775/102653_CAM10_1Bed_ShortLet_IMG_07_0000.jpeg",
                    "https://media.rightmove.co.uk/103k/102653/99838775/102653_CAM10_1Bed_ShortLet_IMG_06_0000.jpeg",
                    "https://media.rightmove.co.uk/103k/102653/99838775/102653_CAM10_1Bed_ShortLet_IMG_01_0000.jpeg",
                    "https://media.rightmove.co.uk/103k/102653/99838775/102653_CAM10_1Bed_ShortLet_IMG_00_0000.jpeg",
            },
            {
                    "https://media.rightmove.co.uk/214k/213929/76185228/213929_4887960_IMG_01_0001.jpg",
                    "https://media.rightmove.co.uk/214k/213929/76185228/213929_4887960_IMG_02_0001.jpg",
                    "https://media.rightmove.co.uk/214k/213929/76185228/213929_4887960_IMG_05_0001.jpg",
                    "https://media.rightmove.co.uk/214k/213929/76185228/213929_4887960_IMG_07_0001.jpg",
                    "https://media.rightmove.co.uk/214k/213929/76185228/213929_4887960_IMG_08_0001.jpg",
                    "https://media.rightmove.co.uk/214k/213929/76185228/213929_4887960_IMG_09_0000.jpg",
                    "https://media.rightmove.co.uk/214k/213929/76185228/213929_4887960_IMG_14_0000.jpg",
            },
            {

                    "https://media.rightmove.co.uk/51k/50795/87826444/50795_P4484M3322_IMG_05_0000.jpg",
                    "https://media.rightmove.co.uk/51k/50795/87826444/50795_P4484M3322_IMG_09_0000.jpg",
                    "https://media.rightmove.co.uk/51k/50795/87826444/50795_P4484M3322_IMG_08_0000.jpg",
                    "https://media.rightmove.co.uk/51k/50795/87826444/50795_P4484M3322_IMG_02_0000.jpg",
                    "https://media.rightmove.co.uk/51k/50795/87826444/50795_P4484M3322_IMG_03_0000.jpg",
                    "https://media.rightmove.co.uk/51k/50795/87826444/50795_P4484M3322_IMG_04_0000.jpg",
            },
            {
                    "https://media.rightmove.co.uk/217k/216443/101689511/216443_403WS2_IMG_00_0000.jpeg",
                    "https://media.rightmove.co.uk/217k/216443/101689511/216443_403WS2_IMG_01_0000.jpeg",
                    "https://media.rightmove.co.uk/217k/216443/101689511/216443_403WS2_IMG_02_0000.jpeg",
                    "https://media.rightmove.co.uk/217k/216443/101689511/216443_403WS2_IMG_03_0000.jpeg",
                    "https://media.rightmove.co.uk/217k/216443/101689511/216443_403WS2_IMG_04_0000.jpeg",
                    "https://media.rightmove.co.uk/217k/216443/101689511/216443_403WS2_IMG_06_0000.jpeg",
                    "https://media.rightmove.co.uk/217k/216443/101689511/216443_403WS2_IMG_07_0000.jpeg",
                    "https://media.rightmove.co.uk/217k/216443/101689511/216443_403WS2_IMG_08_0000.jpeg",
                    "https://media.rightmove.co.uk/217k/216443/101689511/216443_403WS2_IMG_09_0000.jpeg",
            },
            // berlin
            {
                "https://cdn.pixabay.com/photo/2014/08/11/21/40/wall-416062__340.jpg",
                "https://cdn.pixabay.com/photo/2014/08/11/21/39/wall-416060__340.jpg",
                "https://cdn.pixabay.com/photo/2020/12/16/00/10/home-5835289__340.jpg",
                "https://cdn.pixabay.com/photo/2017/02/24/12/24/bathroom-2094733__340.jpg",
                "https://cdn.pixabay.com/photo/2015/11/27/20/28/florence-1066314__340.jpg"
            },

            {
                "https://cdn.pixabay.com/photo/2017/03/10/10/07/bathroom-2132342__340.jpg",
                "https://cdn.pixabay.com/photo/2016/11/19/13/06/bed-1839183__340.jpg",
                "https://cdn.pixabay.com/photo/2017/09/09/18/25/living-room-2732939__340.jpg",
                "https://cdn.pixabay.com/photo/2016/12/30/07/59/kitchen-1940174__340.jpg",
                "https://cdn.pixabay.com/photo/2016/05/28/21/34/old-town-1422155__340.jpg"
            },
            {
                "https://cdn.pixabay.com/photo/2016/02/29/11/41/bathroom-1228427__340.jpg",
                "https://cdn.pixabay.com/photo/2016/11/18/13/02/bed-1834327__340.jpg",
                "https://cdn.pixabay.com/photo/2017/08/02/01/01/living-room-2569325__340.jpg",
                "https://cdn.pixabay.com/photo/2017/08/01/12/43/kitchen-2565105__340.jpg",
                "https://cdn.pixabay.com/photo/2017/07/05/16/40/home-2475173__340.jpg"
            },
            {
                "https://cdn.pixabay.com/photo/2018/07/26/10/36/bathroom-3563272__340.jpg",
                "https://cdn.pixabay.com/photo/2020/11/24/11/36/bedroom-5772286__340.jpg",
                "https://cdn.pixabay.com/photo/2017/03/19/01/43/living-room-2155376__340.jpg",
                "https://cdn.pixabay.com/photo/2016/01/31/14/32/architecture-1171462__340.jpg",
                "https://cdn.pixabay.com/photo/2016/10/12/13/07/berlin-1734368__340.jpg"
            },
            {
                "https://cdn.pixabay.com/photo/2016/08/26/15/06/bathroom-1622403__340.jpg",
                "https://cdn.pixabay.com/photo/2020/10/18/09/16/bedroom-5664221__340.jpg",
                "https://cdn.pixabay.com/photo/2016/08/26/15/06/home-1622401__340.jpg",
                "https://cdn.pixabay.com/photo/2016/04/18/08/50/kitchen-1336160__340.jpg",
                "https://cdn.pixabay.com/photo/2017/08/29/12/20/germany-2693092__340.jpg"
            },
            {
                "https://cdn.pixabay.com/photo/2017/02/24/12/24/bathroom-2094735__340.jpg",
                "https://cdn.pixabay.com/photo/2016/12/30/07/55/bedroom-1940168__340.jpg",
                "https://cdn.pixabay.com/photo/2017/03/19/01/18/living-room-2155353__340.jpg",
                "https://cdn.pixabay.com/photo/2017/03/25/23/32/kitchen-2174593__340.jpg",
                "https://cdn.pixabay.com/photo/2014/12/10/14/01/berlin-563101__340.jpg"
            },
                {
                        "https://pixabay.com/photos/bathroom-sink-mirror-apartment-2094716/",
                        "https://cdn.pixabay.com/photo/2015/03/26/09/42/bedroom-690129__340.jpg",
                        "https://pixabay.com/photos/couch-furnitures-indoors-1835923/",
                        "https://cdn.pixabay.com/photo/2017/03/22/17/39/kitchen-2165756__340.jpg",
                        "https://pixabay.com/photos/alley-road-city-historic-center-4054361/"
                }
        };

        Long[] hostIds = {
            1L, // budapest
            1L,
            2L,
            3L,
            3L,
            3L,
            4L,
            5L, // berlin
            6L,
            6L,
            7L,
            8L,
            8L,
            8L
        };

        for (int i = 0; i < descriptions.length; i++) {
            NewAccommodation newAccommodation = NewAccommodation.builder()
                    .city(i < descriptions.length / 2 ? cities[0] : cities[1])
                    .country(i < descriptions.length / 2 ? countries[0] : countries[1])
                    .street(streets[i])
                    .zipCode(zipCodes[i])
                    .houseNumber(houseNumbers[i])
                    .latitude(latitudes[i])
                    .longitude(longitudes[i])
                    .description(descriptions[i])
                    .maxNumberOfGuests(maxNumbersOfGuests[i])
                    .name(names[i])
                    .pictures(Arrays.asList(pictureUrls[i]))
                    .hostId(hostIds[i])
                    .build();

            Accommodation accommodation = createAccommodation(newAccommodation);
            repository.saveAndFlush(accommodation);
        }


        NewAccommodation newAccommodation = NewAccommodation.builder()
                .city("Kazincbarcika")
                .country("Hungary")
                .street("Utca")
                .zipCode("4444")
                .houseNumber("12")
                .latitude(22.00)
                .longitude(32.00)
                .description("Nice!444négy")
                .maxNumberOfGuests(4000)
                .name("Házikó")
                .pictures(null)
                .hostId(1L)
                .build();

        Accommodation accommodation = createAccommodation(newAccommodation);
        repository.save(accommodation);
    }


    private Accommodation createAccommodation(NewAccommodation newAccommodation) {
        Address address = Address.builder()
                .city(newAccommodation.getCity())
                .country(newAccommodation.getCountry())
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
                .pictures(newAccommodation.getPictures())
                .build();

        return accommodation;
    }
}
