-- Table: product.master_product

-- DROP TABLE IF EXISTS product.master_product;

CREATE TABLE IF NOT EXISTS product.master_product
(
    id bigint NOT NULL DEFAULT nextval('product.master_product_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    price numeric(38,2) NOT NULL,
    category character varying(255) COLLATE pg_catalog."default",
    created_at bigint,
    created_by character varying(255) COLLATE pg_catalog."default",
    description character varying(255) COLLATE pg_catalog."default",
    updated_at bigint,
    updated_by character varying(255) COLLATE pg_catalog."default",
    active boolean NOT NULL,
    CONSTRAINT master_product_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS product.master_product
    OWNER to postgres;

-- Table: product.master_product_attributes

-- DROP TABLE IF EXISTS product.master_product_attributes;

CREATE TABLE IF NOT EXISTS product.master_product_attributes
(
    master_product_id bigint NOT NULL,
    attributes character varying(255) COLLATE pg_catalog."default",
    attributes_key character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT master_product_attributes_pkey PRIMARY KEY (master_product_id, attributes_key),
    CONSTRAINT fkavw0b83mexfaqglvob48brh2a FOREIGN KEY (master_product_id)
        REFERENCES product.master_product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS product.master_product_attributes
    OWNER to postgres;

-- Table: product.variant_product

-- DROP TABLE IF EXISTS product.variant_product;

CREATE TABLE IF NOT EXISTS product.variant_product
(
    id bigint NOT NULL DEFAULT nextval('product.variant_product_id_seq'::regclass),
    active boolean NOT NULL,
    additional_price numeric(38,2),
    color character varying(255) COLLATE pg_catalog."default",
    created_at bigint,
    created_by character varying(255) COLLATE pg_catalog."default",
    material character varying(255) COLLATE pg_catalog."default",
    quantity integer,
    size character varying(255) COLLATE pg_catalog."default",
    sku character varying(255) COLLATE pg_catalog."default",
    updated_at bigint,
    updated_by character varying(255) COLLATE pg_catalog."default",
    master_product_id bigint NOT NULL,
    CONSTRAINT variant_product_pkey PRIMARY KEY (id),
    CONSTRAINT fkqt9s9wr6vrs1hwxubst58h4ho FOREIGN KEY (master_product_id)
        REFERENCES product.master_product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS product.variant_product
    OWNER to postgres;

-- Table: product.variant_product_attributes

-- DROP TABLE IF EXISTS product.variant_product_attributes;

CREATE TABLE IF NOT EXISTS product.variant_product_attributes
(
    variant_product_id bigint NOT NULL,
    attributes character varying(255) COLLATE pg_catalog."default",
    attributes_key character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT variant_product_attributes_pkey PRIMARY KEY (variant_product_id, attributes_key),
    CONSTRAINT fkdk5l8lis2q9dkpgff3lvecfyk FOREIGN KEY (variant_product_id)
        REFERENCES product.variant_product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS product.variant_product_attributes
    OWNER to postgres;