high-availability {
    vrrp {
        group langroup1 {
            interface eth1
            virtual-address 10.0.5.1/24
            vrid 10
        }
        group wangroup4 {
            interface eth0
            virtual-address 10.0.17.74/24
            vrid 154
        }
    }
}
interfaces {
    ethernet eth0 {
        address 10.0.17.54/24
        description WAN
        hw-id 00:50:56:b3:00:e4
    }
    ethernet eth1 {
        address 10.0.5.3/24
        description LAN
        hw-id 00:50:56:b3:e5:c4
    }
    loopback lo {
    }
}
nat {
    destination {
        rule 1 {
            description "Port Forwarding WAN -> Web-01(HTTP)"
            destination {
                port 80
            }
            inbound-interface eth0
            protocol tcp
            translation {
                address 10.0.5.100
            }
        }
        rule 2 {
            description "Port Forwarding WAN -> Web-01(SSH)"
            destination {
                port 22
            }
            inbound-interface eth0
            protocol tcp
            translation {
                address 10.0.5.100
            }
        }
    }
    source {
        rule 1 {
            outbound-interface eth0
            source {
                address 10.0.5.0/24
            }
            translation {
                address masquerade
            }
        }
    }
}
protocols {
    static {
        route 0.0.0.0/0 {
            next-hop 10.0.17.2 {
            }
        }
    }
}
service {
    dns {
        forwarding {
            allow-from 10.0.5.0/24
            listen-address 10.0.5.3
            listen-address 10.0.5.1
        }
    }
    ssh {
        listen-address 0.0.0.0
    }
}
system {
    config-management {
        commit-revisions 100
    }
    console {
        device ttyS0 {
            speed 115200
        }
    }
    host-name vyos-02
    login {
        user vyos {
            authentication {
                encrypted-password $6$lEgayS1Fn7$Y4.VS/yBAdhxkmgDAzrzUN/ibgmO9rRgblmnMMxvFqN1D89m/XTqRxk7.DW60cj5Dvv9PC5c0n0V3M7weIk9v/
                plaintext-password ""
            }
        }
    }
    name-server 10.0.17.2
    ntp {
        server 0.pool.ntp.org {
        }
        server 1.pool.ntp.org {
        }
        server 2.pool.ntp.org {
        }
    }
    syslog {
        global {
            facility all {
                level info
            }
            facility protocols {
                level debug
            }
        }
    }
}


// Warning: Do not remove the following line.
// vyos-config-version: "broadcast-relay@1:cluster@1:config-management@1:conntrack@1:conntrack-sync@1:dhcp-relay@2:dhcp-server@5:dhcpv6-server@1:dns-forwarding@3:firewall@5:https@2:interfaces@18:ipoe-server@1:ipsec@5:l2tp@3:lldp@1:mdns@1:nat@5:ntp@1:pppoe-server@5:pptp@2:qos@1:quagga@6:salt@1:snmp@2:ssh@2:sstp@3:system@20:vrrp@2:vyos-accel-ppp@2:wanloadbalance@3:webgui@1:webproxy@2:zone-policy@1"
// Release version: 1.3-rolling-202012291104
