-- Players
CREATE TABLE players (
  id uuid PRIMARY KEY,
  username varchar(30) NOT NULL,
  created_at timestamptz NOT NULL DEFAULT now()
);

CREATE UNIQUE INDEX ux_players_username ON players (username);

-- Games
CREATE TABLE games (
  id uuid PRIMARY KEY,
  white_player_id uuid NOT NULL REFERENCES players(id),
  black_player_id uuid NOT NULL REFERENCES players(id),
  played_at timestamptz NOT NULL,
  game_result varchar(10) NOT NULL,
  created_at timestamptz NOT NULL DEFAULT now(),
  CONSTRAINT ck_games_players_different CHECK (white_player_id <> black_player_id),
  CONSTRAINT ck_games_result CHECK (game_result IN ('WHITE_WIN','BLACK_WIN','DRAW'))
);

CREATE INDEX ix_games_white_played_at ON games (white_player_id, played_at DESC);
CREATE INDEX ix_games_black_played_at ON games (black_player_id, played_at DESC);
CREATE INDEX ix_games_played_at ON games (played_at DESC);
CREATE INDEX ix_games_result ON games (game_result);

-- Moves
CREATE TABLE moves (
  id uuid PRIMARY KEY,
  game_id uuid NOT NULL REFERENCES games(id) ON DELETE CASCADE,
  move_number int NOT NULL,
  move_color varchar(5) NOT NULL,
  san varchar(20) NOT NULL,
  created_at timestamptz NOT NULL DEFAULT now(),
  CONSTRAINT ck_moves_move_number CHECK (move_number >= 1),
  CONSTRAINT ck_moves_color CHECK (move_color IN ('WHITE','BLACK')),
  CONSTRAINT ux_moves_game_move_color UNIQUE (game_id, move_number, move_color)
);

CREATE INDEX ix_moves_game_move_number ON moves (game_id, move_number);
CREATE INDEX ix_moves_san ON moves (san);