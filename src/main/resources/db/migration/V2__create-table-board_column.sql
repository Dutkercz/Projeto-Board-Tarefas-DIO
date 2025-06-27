CREATE TABLE board_column(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    kind VARCHAR(15) NOT NULL,
    _order INT NOT NULL,
    board_id BIGINT NOT NULL,

    CONSTRAINT fk_board_column__boards
        FOREIGN KEY (board_id) REFERENCES board(id),
    CONSTRAINT id_order_uk UNIQUE KEY unique_board_id_order(board_id, _order)
) ENGINE=InnoDB;

CREATE TABLE card (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    description VARCHAR(150),
    created_at DATE,
    board_column_id BIGINT NOT NULL,

    CONSTRAINT fk_card__board_column
        FOREIGN KEY (board_column_id) REFERENCES board_column(id)
)ENGINE=InnoDB;

CREATE TABLE block(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    block_cause VARCHAR(255) NOT NULL,
    block_in DATE,
    unblock_cause VARCHAR(255) NOT NULL,
    un_block DATE,
    card_id BIGINT NOT NULL,

    CONSTRAINT fk_block__card
        FOREIGN KEY (card_id) REFERENCES card(id)
)ENGINE=InnoDB;

