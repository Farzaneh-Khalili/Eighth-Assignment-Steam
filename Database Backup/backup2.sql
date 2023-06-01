PGDMP      "        	            {            steam    15.2    15.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16400    steam    DATABASE     �   CREATE DATABASE steam WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.utf8';
    DROP DATABASE steam;
                postgres    false            �            1259    16413    account    TABLE     t   CREATE TABLE public.account (
    id text,
    username text NOT NULL,
    password text,
    date_of_birth date
);
    DROP TABLE public.account;
       public         heap    postgres    false            �            1259    16418    download    TABLE     d   CREATE TABLE public.download (
    account_id text,
    game_id text,
    download_count integer
);
    DROP TABLE public.download;
       public         heap    postgres    false            �            1259    16408    game    TABLE     �   CREATE TABLE public.game (
    "id " text,
    title text,
    developer text,
    genre text,
    price double precision,
    release_year integer,
    controller_support boolean,
    reviews integer,
    size integer,
    file_path text
);
    DROP TABLE public.game;
       public         heap    postgres    false            �          0    16413    account 
   TABLE DATA           H   COPY public.account (id, username, password, date_of_birth) FROM stdin;
    public          postgres    false    215   �
       �          0    16418    download 
   TABLE DATA           G   COPY public.download (account_id, game_id, download_count) FROM stdin;
    public          postgres    false    216   )       �          0    16408    game 
   TABLE DATA           �   COPY public.game ("id ", title, developer, genre, price, release_year, controller_support, reviews, size, file_path) FROM stdin;
    public          postgres    false    214   F       m           2606    16424    account account_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (username);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public            postgres    false    215            �   X   x^%�1� @ѹ��%4։x�C#	NrzQ����v���|��(�>����X1�pe�{���1o���ˋ�Z)`"����:      �      x^����� � �      �   O  x^���n�0���S�Ra�\�k��]�BۑF�8p
n�F�I'}�1�Tӹ���H|��B��%I k�N�#	�vP�D��ƺe��jE�Fk����4���������w8��](l8�Ⱥq���ZY��[Eǹ5%睐��Wq�hh8ߢՃ)��YsիzA)�I��6��~��d-�Y�o�!�T�I��~@cd�
�)�KQ�w��Wy��a��Y
9e�W�ғ:�i�+�FQ��U>ۭ.��	C�E��o-;ii�y iJ�@;	O�<a�;� ���A��y��FX����9ȃh�����s�tD2��W '،c���mZO$[�j�3�+�{��[��o�q|rN0G\i]>�4�	�3���}𢑗B�+}�!:�f^Q ���ҕ]�c[��A9X��7�_q��F�exnF6ZH�/�N������Kk]?�=PJv��Jj�3�a}�W/~�}�}�^D3fv���?ꎬZ�ʣ%[��v���Џ��NZߏ�ޚ�� �Xr����8���T�qd)J��֒�kR�Fv�豔~���]���^"�)�O�u�z��ݐ��_��|�	~�X,~5q4     