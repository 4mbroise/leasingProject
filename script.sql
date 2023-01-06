create table if not exists employee
(
    memberId         int auto_increment,
    name             varchar(100) not null,
    surname          varchar(100) not null,
    socialSecurityId varchar(100) not null,
    driverLicenseId  varchar(100) not null,
    adress           varchar(100) not null,
    constraint employee_pk
    primary key (memberId)
    );

create table if not exists vehicle
(
    registration varchar(100) not null,
    type         varchar(100) not null,
    brand         varchar(100) not null,
    model         varchar(100) not null,
    motorType         varchar(100) not null,
    gearBox         varchar(100) not null,
    description         varchar(500) not null,
    constraint vehicle_pk
    primary key (registration)
    );

create table if not exists rent
(
    rentId        int          not null,
    renter        int          not null,
    rentedVehicle varchar(100) not null,
    rentDate      DATE         not null,
    hourStartRent TIME         not null,
    hoursEndRent  TIME         not null,
    constraint rent_pk
    primary key (rentId),
    constraint rentedVehicle
    foreign key (rentedVehicle) references vehicle (registration),
    constraint renter
    foreign key (renter) references employee (memberId)
    );
