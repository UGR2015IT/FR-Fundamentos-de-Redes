##STUDENTS IN THE GROUP:
-Davide Gallitelli
-Giulia Angarano

##CHOSEN PROTOCOL: 6LoWPAN

##RESUME:

The IPv6 protocol is born with the goal in mind to provide IP communication capabilities to a WSN Node (and by extension, to a WSN Network).
Its name comes from the two protocols he is fusion of, IPv6, the new and yet-to-be-adopted Internet protocol, with WPAN, Wireless Personal Area Network, a low power network ideal for sensors and actuators.
This protocol is supposed to assign to the outer nodes of the network, whether FFD Full Functioning Device or RFD Reduced Functioning Device, one of the 2^128 possible routable IP addresses (IPv6), keeping the 802.15.4 communication inside of it (like RFID @ 2,4 GHz). This is achieved by putting a 6LoWPAN adaptation layer on top of the Physical and Data Link layers (standardized on the IEEE 802.15.4), and by allowing custom Application Layers using Socket Interfaces.
The adaptation layer adds TCP/IP compression, packet fragmentation and routing.
Considering that MAC MTU (Maximum Transfer Unit) is made of 127 bytes, 46 of which are needed for IEEE 802.15.4, 40 for IP header, 20 for TCP header or 8 for UDP header, then very few bytes are left for data. The solution for this problem is in the Header TCP/IP Compression. 
6LoWPAN requires all fragments of the IP Packet to carry the same “tag”, assigned sequentially at the source of fragmentation, to make sure that applications would not have problems because of the very large IP packets compared to the 802.15.4.
Moreover, the border nodes of the WSN should be able to route IPv6 packets into the WSN nodes from outside and route inside packets to outside IP network.
The target of the 6LoWPAN protocol are applications that need wireless internet connectivity at lower data rates for devices with low computational and storage capabilities: home and factory automation, smart grids, real-time environmental systems. A lot of plausible application are available and easy to be put in practice, some of them will be shown during the presentation.
